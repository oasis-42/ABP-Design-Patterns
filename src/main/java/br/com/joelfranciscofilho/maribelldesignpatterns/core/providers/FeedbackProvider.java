package br.com.joelfranciscofilho.maribelldesignpatterns.core.providers;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackProcessResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Theme;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.exceptions.NetworkErrorException;

public interface FeedbackProvider {
    FeedbackResponse processFeedback(String text, Theme theme) throws NetworkErrorException;
}
