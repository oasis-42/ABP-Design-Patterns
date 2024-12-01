package br.com.joelfranciscofilho.maribelldesignpatterns.web.adapters;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.GrammarFixProvider;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.stereotype.Service;

@Service
public class OpenAiGrammarFixImpl implements GrammarFixProvider {
    private final OpenAiChatModel chatModel;

    String jsonSchema = """
            {
              "$schema": "http://json-schema.org/draft-07/schema#",
              "title": "Generated schema for Root",
              "type": "object",
              "properties": {
                "text": {
                  "type": "string"
                }
              },
              "required": [
                "text"
              ],
              "additionalProperties": false
            }
            """;

    public OpenAiGrammarFixImpl(OpenAiChatModel chatCompletion) {
        this.chatModel = chatCompletion;
    }

    @Override
    public String tryFixGrammar(String text) {
        String promptMessage = """
             Você é um assistente que receberá um texto em portugues vindo de um OCR e corrigirá as
             palavras que achar incorretas visando um português valido, não criará um texto novo, apenas corrigirá
             as palavras que o OCR trouxer com gramática errada e retornará a resposta
             em JSON com uma propriedade 'text'
             """;

        Prompt prompt = new Prompt(promptMessage + text,
                OpenAiChatOptions.builder()
                        .withModel(OpenAiApi.ChatModel.GPT_4_O)
                        .withResponseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, this.jsonSchema))
                        .build());

        ChatResponse chatResponse = this.chatModel.call(prompt);
        String content = chatResponse.getResult().getOutput().getContent();

        BeanOutputConverter<CorrectedOcrResponse> outputConverter = new BeanOutputConverter<>(CorrectedOcrResponse.class);

        CorrectedOcrResponse correctedOcrResponse = outputConverter.convert(content);

        if (correctedOcrResponse == null) return text;

        return correctedOcrResponse.text;
    }

    record CorrectedOcrResponse(@JsonProperty(required = true, value = "text") String text) {
    }
}