package br.com.joelfranciscofilho.maribelldesignpatterns.web.adapters;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.OcrResponse;
import br.com.joelfranciscofilho.maribelldesignpatterns.core.providers.OcrProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.vision.v1p1beta1.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class GoogleCloudVisionOcrImpl implements OcrProvider {

    private final ImageAnnotatorSettings settings;

    public GoogleCloudVisionOcrImpl(@Value("${google.cloud.vision.credentials}") String serviceAccountJson) throws IOException {
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(
                new java.io.ByteArrayInputStream(serviceAccountJson.getBytes())
        );

        this.settings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();
    }

    @Override
    public OcrResponse processOcr(String base64Image) throws IOException {
        byte[] decoded = Base64.getDecoder().decode(base64Image);

        Image image = Image.newBuilder()
                .setContent(com.google.protobuf.ByteString.copyFrom(decoded))
                .build();

        Feature feature = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();

        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feature)
                .setImage(image)
                .build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create(settings)) {
            AnnotateImageResponse response = client.batchAnnotateImages(List.of(request))
                    .getResponses(0);

            Double averageConfidence = response.getFullTextAnnotation().getPagesList()
                    .stream()
                    .flatMap(page -> page.getBlocksList().stream())
                    .mapToDouble(Block::getConfidence)
                    .summaryStatistics()
                    .getAverage();

            return new OcrResponse(response.getFullTextAnnotation().getText(), averageConfidence);
        }
    }
}