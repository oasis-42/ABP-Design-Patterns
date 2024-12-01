package br.com.joelfranciscofilho.maribelldesignpatterns.core.providers;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.OcrResponse;

import java.io.IOException;

public interface OcrProvider {
    OcrResponse processOcr(String base64Image) throws IOException;
}