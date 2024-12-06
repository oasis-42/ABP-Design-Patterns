package br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.Subject;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackRefinedEssayDto;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Feedback;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Theme;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.repositories.FeedbackRepository;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.repositories.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutomaticFeedbackService extends Subject implements FeedbackService {

    private final ThemeRepository themeRepository;
    private final FeedbackRepository feedbackRepository;
    private final List<FeedbackCreatedEvent> events = new ArrayList<>();

    public AutomaticFeedbackService(ThemeRepository themeRepository,
                                    FeedbackRepository feedbackRepository,
                                    AutomaticFeedbackConsumer automaticFeedbackConsumer) {
        this.themeRepository = themeRepository;
        this.feedbackRepository = feedbackRepository;

        this.registerObserver(automaticFeedbackConsumer);
    }

    @Override
    public FeedbackProcessResponse processFeedback(String text, Long themeId) throws Exception {
        Theme theme = themeRepository.findById(themeId).orElseThrow();
        Feedback feedback = Feedback.of(theme);
        Feedback saved = feedbackRepository.save(feedback);
        saved.setText(text);
        feedbackRepository.save(feedback);
        events.add(new FeedbackCreatedEvent(saved.getFeedbackId(), text, theme, Feedback.Status.CREATED));
        notifyObservers();
        return new FeedbackProcessResponse(saved.getFeedbackId());
    }

    public void findById(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow();

        FeedbackRefinedEssayDto.RefinedEssay.builder()
                .paragraphType("introduction")
                .refinedText(feedback.getRefinedEssayText().getIntroduction())
                .originalText(feedback.getOriginalEssayText().getIntroduction())
                .build();

        FeedbackRefinedEssayDto.RefinedEssay.builder()
                .paragraphType("development")
                .refinedText(feedback.getRefinedEssayText().getDevelopment())
                .originalText(feedback.getOriginalEssayText().getDevelopment())
                .build();

        FeedbackRefinedEssayDto.RefinedEssay.builder()
                .paragraphType("conclusion")
                .refinedText(feedback.getRefinedEssayText().getConclusion())
                .originalText(feedback.getOriginalEssayText().getConclusion())
                .build();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> events.forEach(event -> {
            if (event.status() == Feedback.Status.CREATED) observer.update(event);
        }));

        observers.clear();
    }
}
