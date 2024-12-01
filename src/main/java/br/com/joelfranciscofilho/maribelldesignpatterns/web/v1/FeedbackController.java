package br.com.joelfranciscofilho.maribelldesignpatterns.web.v1;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackProcessRequest;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackProcessResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.entities.Theme;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.exceptions.NetworkErrorException;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.FeedbackProvider;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/feedbacks")
@AllArgsConstructor
public class FeedbackController {

    private final ThemeRepository themeRepository;
    private final FeedbackProvider feedbackProvider;

    @PostMapping
    public ResponseEntity<FeedbackProcessResponse> processFeedback(@RequestBody @Valid FeedbackProcessRequest dto)
            throws NetworkErrorException {

        Theme theme = themeRepository.findById(dto.themeId()).orElseThrow();

        FeedbackProcessResponse response = feedbackProvider.processFeedback(
                new FeedbackProvider.FeedbackProcessDto(dto.text(), theme));

        return ResponseEntity.ok(response);
    }
}
