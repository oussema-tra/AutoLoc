package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    public AgenceServiceImpl(IAgenceRepository agenceRepository) {
        super(agenceRepository);   // ← appelle le constructeur parent
        this.agenceRepository = agenceRepository;
    }

    @Override
    public String getEntityName() {
        return "Agence";
    }

    @Override
    public Long getId(Agence entity) {
        return entity.getIdAgence();
    }

    @Override
    public void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        if (agence == null) return false;
        if (!Utils.isNotEmpty(agence.getNom())) return false;
        if (!Utils.isNotEmpty(agence.getVille())) return false;
        return !isNew || agence.getIdAgence() == null;
    }

    @Override
    public boolean hasVehicles(Long id) {
        Agence agence = findById(id);
        return agence.getVehicules() != null && !agence.getVehicules().isEmpty();
    }
}