package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import java.util.List;

public record FeedbackProcessResponse(List<RefinedEssay> refinedEssay, List<EssayAnalysis> essayAnalysis) {
    public record RefinedEssay(String paragraphType, String originalText, String refinedText) {
    }

    public record EssayAnalysis(
            Integer analyzedSkill, Integer grade, String feedback, List<Success> successes, List<Error> errors) {

        public record Success(String except, String reason) {
        }

        public record Error(String except, String reason, String howToCorrect) {
        }
    }
}
