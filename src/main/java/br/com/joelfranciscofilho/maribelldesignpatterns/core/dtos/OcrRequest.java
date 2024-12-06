package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

public record OcrRequest(@JsonProperty("base64") @Valid String base64Image) {
}
