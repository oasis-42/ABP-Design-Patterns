package br.com.joelfranciscofilho.maribelldesignpatterns.core.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    @EqualsAndHashCode.Include
    private Long themeId;

    @Column(name = "title")
    @Max(255)
    private String title;

    @Column(name = "test_year")
    private Long year;

    @OneToMany(mappedBy = "theme")
    private List<Feedback> feedbacks;
}
