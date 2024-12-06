package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record FeedbackRefinedEssayDto(@JsonProperty("refinedEssay") List<RefinedEssay> refinedEssays) {
    @Builder
    public record RefinedEssay(@JsonProperty("paragraphType") String paragraphType,
                               @JsonProperty("originalText") String originalText,
                               @JsonProperty("refinedText") String refinedText) {
    }
}
