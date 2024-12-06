package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record FeedbackEssayAnalysisDto(@JsonProperty("essayAnalysis") List<EssayAnalysis> essayAnalyses) {
    @Builder
    public record EssayAnalysis(
            @JsonProperty("analyzedSkill") Integer analyzedSkill,
            @JsonProperty("grade") Integer grade,
            @JsonProperty("feedback") String feedback,
            @JsonProperty("successes") List<Success> successes,
            @JsonProperty("errors") List<Error> errors) {

        @Builder
        public record Success(@JsonProperty("except") String except,
                              @JsonProperty("reason") String reason) {
        }

        @Builder
        public record Error(@JsonProperty("except") String except,
                            @JsonProperty("reason") String reason,
                            @JsonProperty("howToCorrect") String howToCorrect) {
        }
    }
}
