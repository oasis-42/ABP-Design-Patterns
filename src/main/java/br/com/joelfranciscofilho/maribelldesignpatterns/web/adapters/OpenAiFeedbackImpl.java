package br.com.joelfranciscofilho.maribelldesignpatterns.web.adapters;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackProcessResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackEssayAnalysisDto;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackRefinedEssayDto;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Theme;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.exceptions.NetworkErrorException;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.prompts.PromptMessageAnalysedEssays;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.prompts.PromptMessageRefinedEssays;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.FeedbackProvider;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.schemas.AnalysisEssayJsonSchema;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.schemas.RefinedEssayJsonSchema;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpenAiFeedbackImpl implements FeedbackProvider {
    private final OpenAiChatModel chatModel;

    @Override
    public FeedbackResponse processFeedback(String text, Theme theme) throws NetworkErrorException {
        String filterByTheme = "Baseado no tema do enem de "
                + theme.getYear() + ", " + theme.getTitle() + ". ";

        Prompt promptRefinedEssay = new Prompt(filterByTheme + PromptMessageRefinedEssays.get() + text,
                OpenAiChatOptions.builder()
                        .withModel(OpenAiApi.ChatModel.GPT_4_O)
                        .withResponseFormat(new ResponseFormat(
                                ResponseFormat.Type.JSON_SCHEMA, RefinedEssayJsonSchema.get()))
                        .build());

        ChatResponse chatResponseRefinedEssay = this.chatModel.call(promptRefinedEssay);
        String contentRefinedEssay = chatResponseRefinedEssay.getResult().getOutput().getContent();

        BeanOutputConverter<FeedbackRefinedEssayDto> outputConverterRefinedEssay = new BeanOutputConverter<>(FeedbackRefinedEssayDto.class);

        FeedbackRefinedEssayDto refinedEssays = outputConverterRefinedEssay.convert(contentRefinedEssay);

        Prompt promptAnalyseEssay = new Prompt(filterByTheme + PromptMessageAnalysedEssays.get() + text,
                OpenAiChatOptions.builder()
                        .withModel(OpenAiApi.ChatModel.GPT_4_O)
                        .withResponseFormat(new ResponseFormat(
                                ResponseFormat.Type.JSON_SCHEMA, AnalysisEssayJsonSchema.get()))
                        .build());

        ChatResponse chatResponseAnalyzeEssay = this.chatModel.call(promptAnalyseEssay);
        String contentAnalyzeEssay = chatResponseAnalyzeEssay.getResult().getOutput().getContent();

        BeanOutputConverter<FeedbackEssayAnalysisDto> outputConverterAnalyzeEssay = new BeanOutputConverter<>(FeedbackEssayAnalysisDto.class);

        FeedbackEssayAnalysisDto analyzedEssays = outputConverterAnalyzeEssay.convert(contentAnalyzeEssay);

        return new FeedbackResponse(refinedEssays.refinedEssays(), analyzedEssays.essayAnalyses());
    }
}
