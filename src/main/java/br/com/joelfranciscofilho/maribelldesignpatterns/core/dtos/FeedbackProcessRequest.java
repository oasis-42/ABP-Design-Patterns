package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public record FeedbackProcessRequest(@NotNull @Max(4000) String text,
                                     @NotNull Long themeId) {
}