package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.domain.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    @Test
    public void addAgence() {
        // Création de l'agence
        Agence agence = new Agence();
        // agence.setIdAgence(8L);   // ❌ NE PAS METTRE
        agence.setAdresse("1 Rue Hedi");
        agence.setNom("Agence ariana");
        agence.setTelephone("71585874");
        agence.setVille("Tunis");

        // Création des véhicules
        Vehicule v1 = new Vehicule();
        // v1.setIdVehicule(12L);    // ❌ NE PAS METTRE
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setImmatriculation("785414TU96");
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setStatut(StatutVehicule.MAINTENANCE);
        v1.setTarifJournalier(new BigDecimal("100"));

        Vehicule v2 = new Vehicule();
        // v2.setIdVehicule(13L);    // ❌ NE PAS METTRE
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setImmatriculation("785414TU95");
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setTarifJournalier(new BigDecimal("80"));

        v1.setAgence(agence);
        v2.setAgence(agence);

        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(v1);
        vehicules.add(v2);
        agence.setVehicules(vehicules);

        agenceRepository.save(agence);
    }

    @Test
    public void loadAgence() {
        Iterable<Agence> agences = agenceRepository.findAll();
        StringBuilder sb = new StringBuilder();

        for (Agence agence : agences) {
            sb.append(agence.getIdAgence()).append(" | ")
                    .append(agence.getNom())
                    .append(" Vehicules Count : ")
                    .append(agence.getVehicules().size())
                    .append(" === ");

            for (Vehicule v : agence.getVehicules()) {
                sb.append(v.getIdVehicule()).append("|")
                        .append(v.getImmatriculation()).append(" === ");
            }
        }

        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}