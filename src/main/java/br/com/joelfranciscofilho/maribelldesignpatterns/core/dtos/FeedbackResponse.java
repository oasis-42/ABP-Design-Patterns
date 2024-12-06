package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record FeedbackResponse(
        @JsonProperty("refinedEssay") List<FeedbackRefinedEssayDto.RefinedEssay> refinedEssay,
        @JsonProperty("essayAnalysis") List<FeedbackEssayAnalysisDto.EssayAnalysis> essayAnalysis) {
}
