package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.services.ProfessorService;
import be.bstorm.exouniversite.dal.models.entities.ProfessorEntity;
import be.bstorm.exouniversite.dal.repositories.ProfessorRepository;
import be.bstorm.exouniversite.pl.models.Professor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    
    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<ProfessorEntity> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public void createProfessor(Professor form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public Optional<ProfessorEntity> getProfessorByNumeroMatricule(String numeroMatricule) {
        return professorRepository.findByNumeroMatricule(numeroMatricule);
    }

    @Override
    public void updateProfessor(String numeroMatricule, Professor form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public void deleteProfessor(String numeroMatricule) {
        professorRepository.deleteByNumeroMatricule(numeroMatricule);
    }
}
