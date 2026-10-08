package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "employe")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    private String nom;
    private String prenom;

    @Enumerated(EnumType.STRING)
    private RoleEmploye role;

    // ⚠️ AJOUTER : relation ManyToOne vers Agence
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agence_id")
    private Agence agence;
}