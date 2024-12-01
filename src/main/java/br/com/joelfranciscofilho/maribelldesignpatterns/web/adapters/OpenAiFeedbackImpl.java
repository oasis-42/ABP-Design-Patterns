package br.com.joelfranciscofilho.maribelldesignpatterns.web.adapters;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackProcessResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.exceptions.NetworkErrorException;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.FeedbackProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAiFeedbackImpl implements FeedbackProvider {

    @Override
    public FeedbackProcessResponse processFeedback(FeedbackProcessDto request) throws NetworkErrorException {
        List<FeedbackProcessResponse.RefinedEssay> refinedEssays = List.of(new FeedbackProcessResponse.RefinedEssay(
                "paragraphType", "originalText", "refinedText"));

        List<FeedbackProcessResponse.EssayAnalysis.Success> successes = List.of(
                new FeedbackProcessResponse.EssayAnalysis.Success("except", "reason"));

        List<FeedbackProcessResponse.EssayAnalysis.Error> errors = List.of(new FeedbackProcessResponse.EssayAnalysis.Error(
                "except", "reason", "howToCorrect"));

        List<FeedbackProcessResponse.EssayAnalysis> essayAnalysis = List.of(
                new FeedbackProcessResponse.EssayAnalysis(1, 1, "", successes, errors));

        return new FeedbackProcessResponse(refinedEssays, essayAnalysis);
    }
}
