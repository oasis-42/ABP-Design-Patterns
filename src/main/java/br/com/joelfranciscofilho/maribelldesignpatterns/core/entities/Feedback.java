package br.com.joelfranciscofilho.maribelldesignpatterns.core.entities;

import br.com.joelfranciscofilho.maribelldesignpatterns.core.dtos.FeedbackResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    @EqualsAndHashCode.Include
    private Long feedbackId;

    @Column(name = "grade")
    private Long grade;

    @Column(name = "quality")
    private Double quality;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "retries")
    private Integer retries;

    @Column(name = "last_retry_datetime")
    private LocalDateTime lastRetryDateTime;

    @OneToOne(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "original_essay_text_id")
    private OriginalEssayText originalEssayText;

    @OneToOne(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "refined_essay_text_id")
    private RefinedEssayText refinedEssayText;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    public void conclude(FeedbackResponse feedbackResponse) {
        this.refinedEssayText = RefinedEssayText.from(this.feedbackId, feedbackResponse.refinedEssay());
        this.originalEssayText.addSteps(feedbackResponse.refinedEssay());
        this.lastRetryDateTime = LocalDateTime.now();
        this.status = Status.FINISHED;
    }

    private Feedback(Theme theme) {
        this.theme = theme;
    }

    public static Feedback of(Theme theme) {
        return new Feedback(theme);
    }

    public void setText(String text) {
        this.originalEssayText = OriginalEssayText.of(this, text);
    }

    public boolean isFinished() {
        return this.status == Status.FAILED || this.status == Status.FINISHED;
    }

    public enum Status {
        CREATED,
        QUEUED,
        PROCESSING,
        FINISHED,
        FAILED
    }
}
