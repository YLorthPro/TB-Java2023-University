package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.ProfessorEntity;
import be.bstorm.exouniversite.pl.models.forms.ProfessorRegister;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<ProfessorEntity> getAllProfessors();
    void createProfessor(ProfessorRegister form);
    Optional<ProfessorEntity> getProfessorByNumeroMatricule(String numeroMatricule);
    void updateProfessor(String numeroMatricule, ProfessorRegister form);
    void deleteProfessor(String numeroMatricule);
}
