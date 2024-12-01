package br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.FeedbackProvider;

public interface FeedbackService {
    FeedbackProcessResponse processFeedback(String text, Long themeId) throws Exception;

    record FeedbackProcessResponse(Long feedbackId) {
    }
}