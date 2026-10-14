package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

@Service
public class EquipementServiceImpl extends GenericServiceImpl<Equipement, Long> implements IEquipementService {

    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Equipement";
    }

    @Override
    public Long getId(Equipement entity) {
        return entity.getIdEquipement();
    }

    @Override
    public void setFields(Equipement source, Equipement target) {
        target.setLibelle(source.getLibelle());
        target.setVehicules(source.getVehicules());
    }
}