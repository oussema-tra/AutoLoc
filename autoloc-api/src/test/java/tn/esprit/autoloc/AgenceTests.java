package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    // ==================== PARTIE I ====================

    private void addAgence(CrudRepository<Agence, Long> repository) {
        Agence agence = new Agence();
        agence.setAdresse("1 Rue Hedi");
        agence.setNom("Agence ariana");
        agence.setTelephone("71585874");
        agence.setVille("Tunis");

        int timestamp = (int) (System.currentTimeMillis() % 100000);

        Vehicule v1 = new Vehicule();
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setImmatriculation("785414TU96" + timestamp);
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setStatut(StatutVehicule.MAINTENANCE);
        v1.setTarifJournalier(new BigDecimal("100"));

        Vehicule v2 = new Vehicule();
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setImmatriculation("785414TU95" + timestamp);
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

        repository.save(agence);
    }

    private void loadAgence(CrudRepository<Agence, Long> repository, String typeDepot) {
        Iterable<Agence> agences = repository.findAll();
        StringBuilder sb = new StringBuilder();

        sb.append("=== Type de dépôt : ").append(typeDepot).append(" ===\n");

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
            sb.append("\n");
        }

        fail(sb.toString());
    }

    @Test
    public void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    public void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    @Test
    public void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "CrudRepository");
    }

    @Test
    public void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "JpaRepository");
    }

    // ==================== PARTIE II ====================

    @Test
    public void loadSortedAgences() {
        Sort sort = Sort.by("idAgence").descending();
        Iterable<Agence> agences = fullAgenceRepository.findAll(sort);

        StringBuilder sb = new StringBuilder();
        sb.append("=== Agences triées par id décroissant ===\n");

        for (Agence agence : agences) {
            sb.append(agence.getIdAgence()).append(" | ")
                    .append(agence.getNom()).append(" | ")
                    .append(agence.getVille()).append("\n");
        }

        fail(sb.toString());
    }

    @Test

    public void loadPagedAgences() {
        Sort sort = Sort.by("idAgence").descending();
        int pageSize = 2;

        // Charger la première page pour connaître le nombre total de pages
        Pageable firstPageable = PageRequest.of(0, pageSize, sort);
        Page<Agence> firstPage = fullAgenceRepository.findAll(firstPageable);

        int totalPages = firstPage.getTotalPages();
        long totalElements = firstPage.getTotalElements();

        StringBuilder sb = new StringBuilder();
        sb.append("=== Agences paginées (taille=").append(pageSize).append(") ===\n");
        sb.append("Total pages : ").append(totalPages).append("\n");
        sb.append("Total éléments : ").append(totalElements).append("\n");
        sb.append("\n");

        // Boucler sur toutes les pages
        for (int i = 0; i < totalPages; i++) {
            Pageable pageable = PageRequest.of(i, pageSize, sort);
            Page<Agence> page = fullAgenceRepository.findAll(pageable);

            sb.append("--- Page ").append(page.getNumber()).append(" ---\n");
            for (Agence agence : page.getContent()) {
                sb.append(agence.getIdAgence()).append(" | ")
                        .append(agence.getNom()).append(" | ")
                        .append(agence.getVille()).append("\n");
            }
            sb.append("\n");
        }

        fail(sb.toString());
    }
}

// Interface dans le même fichier
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}