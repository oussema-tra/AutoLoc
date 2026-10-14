package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employe, Long> implements IEmployeeService {

    public EmployeeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    public String getEntityName() {
        return "Employee";
    }

    @Override
    public Long getId(Employe entity) {
        return entity.getIdEmployee();
    }

    @Override
    public void setFields(Employe source, Employe target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setRole(source.getRole());
        target.setAgence(source.getAgence());
    }
}