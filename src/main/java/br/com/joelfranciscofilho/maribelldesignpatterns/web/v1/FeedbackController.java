package br.com.joelfranciscofilho.maribelldesignpatterns.web.v1;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackProcessRequest;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.factories.FeedbackFactory;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.repositories.FeedbackRepository;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.services.feedback.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/feedbacks")
@AllArgsConstructor
public class FeedbackController {

    private final FeedbackFactory feedbackFactory;

    @PostMapping
    public ResponseEntity<FeedbackService.FeedbackProcessResponse> processFeedback(@RequestBody @Valid FeedbackProcessRequest dto)
            throws Exception {

        FeedbackService feedbackService = this.feedbackFactory.getFeedbackService(dto.type());
        FeedbackService.FeedbackProcessResponse feedbackProcessResponse = feedbackService.processFeedback(dto.text(), dto.themeId());
        return ResponseEntity.ok(feedbackProcessResponse);
    }
}