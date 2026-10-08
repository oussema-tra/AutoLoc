package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numPermis;
    private LocalDate dateInscription;

    // ⚠️ AJOUTER : relation OneToMany vers Reservation
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Builder.Default
    private Set<Reservation> reservations = new HashSet<>();
}