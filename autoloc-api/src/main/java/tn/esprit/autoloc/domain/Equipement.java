package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipement")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private String libelle;

    // ⚠️ AJOUTER : relation ManyToMany (côté inverse)
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Vehicule> vehicules = new HashSet<>();
}