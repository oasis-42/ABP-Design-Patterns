package br.com.joelfranciscofilho.maribelldesignpatterns.core.entities;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackRefinedEssayDto;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "original_essay_texts")
public class OriginalEssayText {
    @Id
    @Column(name = "feedback_id")
    private Long feedbackId;

    @Column(name = "full_original_text", columnDefinition = "TEXT")
    @NotNull
    @Max(4000)
    private String fullOriginalText;

    @Max(4000)
    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;

    @Max(4000)
    @Column(name = "development", columnDefinition = "TEXT")
    private String development;

    @Max(4000)
    @Column(name = "conclusion", columnDefinition = "TEXT")
    private String conclusion;

    @OneToOne
    @MapsId
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    public void addSteps(List<FeedbackRefinedEssayDto.RefinedEssay> dtos) {
        String introduction = dtos.stream()
                .filter(dto -> dto.paragraphType().equals("introduction"))
                .findFirst()
                .orElseThrow()
                .originalText();

        String development = dtos.stream()
                .filter(dto -> dto.paragraphType().equals("development"))
                .findFirst()
                .orElseThrow()
                .originalText();

        String conclusion = dtos.stream()
                .filter(dto -> dto.paragraphType().equals("conclusion"))
                .findFirst()
                .orElseThrow()
                .originalText();

        this.introduction = introduction;
        this.development = development;
        this.conclusion = conclusion;
    }

    private OriginalEssayText(Feedback feedback, String fullOriginalText) {
        this.fullOriginalText = fullOriginalText;
        this.feedback = feedback;
    }

    public static OriginalEssayText of(Feedback feedback, String text) {
        return new OriginalEssayText(feedback, text);
    }
}
