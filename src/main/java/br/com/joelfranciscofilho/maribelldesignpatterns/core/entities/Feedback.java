package br.com.joelfranciscofilho.maribelldesignpatterns.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    public enum Status {
        CREATED,
        QUEUED,
        PROCESSING,
        FINISHED,
        FAILED
    }
}
