package br.com.joelfranciscofilho.maribelldesignpatterns.core.factories;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.enums.FeedbackType;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback.AutomaticFeedbackService;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback.FeedbackService;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback.HumanizedFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeedbackFactory {
    private final AutomaticFeedbackService automaticFeedbackService;
    private final HumanizedFeedbackService humanizedFeedbackService;

    public FeedbackService getFeedbackService(FeedbackType type) {
        return switch (type) {
            case HUMANIZED -> humanizedFeedbackService;
            case AUTOMATIC -> automaticFeedbackService;
        };
    }
}