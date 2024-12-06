package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OcrResponse(
        @JsonProperty(required = true, value = "text") String text,
        @JsonProperty(required = true, value = "confidence") Double confidence) {
}
