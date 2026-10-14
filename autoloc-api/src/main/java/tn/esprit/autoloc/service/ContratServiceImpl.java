package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

@Service
public class ContratServiceImpl extends GenericServiceImpl<Contrat, Long> implements IContratService {

    public ContratServiceImpl(IContratRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Contrat";
    }

    @Override
    public Long getId(Contrat entity) {
        return entity.getIdContrat();
    }

    @Override
    public void setFields(Contrat source, Contrat target) {
        target.setDateSignature(source.getDateSignature());
        target.setMontantTotal(source.getMontantTotal());
        target.setValide(source.getValide());
        target.setReservation(source.getReservation());
        target.setPaiements(source.getPaiements());
    }
}