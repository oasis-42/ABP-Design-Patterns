package br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.enums.FeedbackType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public record FeedbackProcessRequest(@NotNull @Max(4000) String text,
                                     @NotNull Long themeId,
                                     @NotNull FeedbackType type) {
}
