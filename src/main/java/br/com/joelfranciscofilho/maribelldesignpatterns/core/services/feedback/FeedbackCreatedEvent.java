package br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Feedback;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Theme;

public record FeedbackCreatedEvent(Long feedbackId, String text, Theme theme, Feedback.Status status) {
}
