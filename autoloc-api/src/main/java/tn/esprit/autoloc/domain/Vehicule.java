package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    private String immatriculation;
    private String marque;
    private String modele;
    private BigDecimal tarifJournalier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agence_id")
    private Agence agence;

    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    private Set<Reservation> reservations = new HashSet<>();

    // ⚠️ AJOUTER : relation ManyToMany (côté propriétaire)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    @Builder.Default
    private Set<Equipement> equipements = new HashSet<>();
}