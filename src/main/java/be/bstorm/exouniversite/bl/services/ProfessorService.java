package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.ProfessorEntity;
import be.bstorm.exouniversite.pl.models.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<ProfessorEntity> getAllProfessors();
    void createProfessor(Professor form);
    Optional<ProfessorEntity> getProfessorByNumeroMatricule(String numeroMatricule);
    void updateProfessor(String numeroMatricule, Professor form);
    void deleteProfessor(String numeroMatricule);
}
