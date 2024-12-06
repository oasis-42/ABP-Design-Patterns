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
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    @EqualsAndHashCode.Include
    private Long skillId;

    @Max(1)
    @Column(name = "skill_type")
    private String skillType;

    @Max(255)
    @Column(name = "skill_description")
    private String skillDescription;
}
