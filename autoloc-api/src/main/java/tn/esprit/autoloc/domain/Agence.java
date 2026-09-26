package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "agence")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Agence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @NotBlank
    private String nom;

    @NotBlank
    private String ville;

    private String adresse;
    private String telephone;
}