package br.com.joelfranciscofilho.maribelldesignpatterns.web.v1;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/themes")
@AllArgsConstructor
public class ThemesController {
    private final ThemeRepository themeRepository;

    public ResponseEntity<?> getThemes() {
        return ResponseEntity.ok(themeRepository.findAll());
    }
}
