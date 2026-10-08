package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agence")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = {"vehicules", "employees"})
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @NotBlank
    private String nom;

    @NotBlank
    private String ville;

    private String adresse;
    private String telephone;

    @OneToMany(mappedBy = "agence", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Builder.Default
    private Set<Vehicule> vehicules = new HashSet<>();

    // ⚠️ AJOUTER : relation OneToMany vers Employee
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)  // Pas de cascade
    @Builder.Default
    private Set<Employe> employees = new HashSet<>();
}