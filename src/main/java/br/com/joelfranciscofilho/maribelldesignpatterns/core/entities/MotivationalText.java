package br.com.joelfranciscofilho.maribelldesignpatterns.core.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "motivational_texts")
public class MotivationalText {
    @Id
    @Column(name = "motivational_text_id")
    private Long motivationalTextId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;
}
