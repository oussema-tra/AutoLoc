package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    // ⚠️ AJOUTER : relation OneToOne vers Contrat (clé dans reservation)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrat_id", unique = true)
    private Contrat contrat;
}