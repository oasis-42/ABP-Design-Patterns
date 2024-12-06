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
@Table(name = "refined_essay_texts")
public class RefinedEssayText {
    @Id
    @Column(name = "feedback_id")
    private Long feedbackId;

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

    public static RefinedEssayText from(Long feedbackId, List<FeedbackRefinedEssayDto.RefinedEssay> dtos) {
        String introduction = dtos.stream()
                .filter(dto -> dto.paragraphType().equals("introduction"))
                .findFirst()
                .orElseThrow()
                .refinedText();

        String development = dtos.stream()
                .filter(dto -> dto.paragraphType().equals("development"))
                .findFirst()
                .orElseThrow()
                .refinedText();

        String conclusion = dtos.stream()
                .filter(dto -> dto.paragraphType().equals("conclusion"))
                .findFirst()
                .orElseThrow()
                .refinedText();

        return new RefinedEssayText(feedbackId, introduction, development, conclusion, null);
    }
}