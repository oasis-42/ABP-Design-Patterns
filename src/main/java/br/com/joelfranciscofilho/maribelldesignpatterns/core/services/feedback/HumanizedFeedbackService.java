package br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback;

import org.springframework.stereotype.Service;

@Service
public class HumanizedFeedbackService implements FeedbackService {
    @Override
    public FeedbackProcessResponse processFeedback(String text, Long themeId) throws Exception {
        return null;
    }
}