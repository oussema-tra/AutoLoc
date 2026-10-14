package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Long> implements IReservationService {

    public ReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Reservation";
    }

    @Override
    public Long getId(Reservation entity) {
        return entity.getIdReservation();
    }

    @Override
    public void setFields(Reservation source, Reservation target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setStatut(source.getStatut());
        target.setVehicule(source.getVehicule());
        target.setClient(source.getClient());
        target.setContrat(source.getContrat());
    }
}