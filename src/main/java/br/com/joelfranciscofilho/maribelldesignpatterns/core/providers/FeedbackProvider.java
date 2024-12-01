package br.com.joelfranciscofilho.maribelldesignpatterns.core.providers;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackProcessResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Theme;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.exceptions.NetworkErrorException;

public interface FeedbackProvider {
    FeedbackProcessResponse processFeedback(FeedbackProcessDto request) throws NetworkErrorException;

    record FeedbackProcessDto(String text, Theme theme) {
    }
}
