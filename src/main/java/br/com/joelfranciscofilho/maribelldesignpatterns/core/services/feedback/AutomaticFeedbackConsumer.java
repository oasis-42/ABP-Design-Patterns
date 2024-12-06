package br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.Observer;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Feedback;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.exceptions.NetworkErrorException;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.FeedbackProvider;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.repositories.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AutomaticFeedbackConsumer extends Observer<FeedbackCreatedEvent> {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackProvider feedbackProvider;

    @Override
    public void update(FeedbackCreatedEvent event) {
        Thread.ofVirtual().start(() -> {
            try {
                Feedback feedback = feedbackRepository.findById(event.feedbackId()).orElseThrow();

                if (feedback.isFinished()) return;

                FeedbackResponse feedbackResponse = feedbackProvider.processFeedback(event.text(), event.theme());
                feedback.conclude(feedbackResponse);
                feedbackRepository.save(feedback);
            } catch (NetworkErrorException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
