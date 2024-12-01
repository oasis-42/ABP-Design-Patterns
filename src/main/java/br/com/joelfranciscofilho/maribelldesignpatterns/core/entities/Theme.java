package br.com.joelfranciscofilho.maribelldesignpatterns.core.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    @Column(name = "year")
    private Long year;
}
