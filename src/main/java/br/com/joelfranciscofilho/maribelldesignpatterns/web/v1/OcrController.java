package br.com.joelfranciscofilho.maribelldesignpatterns.web.v1;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.OcrRequest;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.OcrResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.GrammarFixProvider;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.OcrProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/ocr")
public class OcrController {

    private final OcrProvider ocrProvider;
    

    public OcrController(OcrProvider ocrProvider) {
        this.ocrProvider = ocrProvider;

    }

    @PostMapping
    public ResponseEntity<OcrResponse> processOcr(@RequestBody @Valid OcrRequest dto) throws IOException {
        OcrResponse ocrResponse = ocrProvider.processOcr(dto.base64Image());
    
        return ResponseEntity.ok(new OcrResponse(ocrResponse.text(), ocrResponse.confidence()));
    }

}