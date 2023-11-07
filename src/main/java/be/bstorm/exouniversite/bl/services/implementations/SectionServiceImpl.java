package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.SectionService;
import be.bstorm.exouniversite.dal.models.entities.SectionEntity;
import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.dal.repositories.CourseRepository;
import be.bstorm.exouniversite.dal.repositories.ProfessorRepository;
import be.bstorm.exouniversite.dal.repositories.SectionRepository;
import be.bstorm.exouniversite.pl.models.forms.SectionForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    public SectionServiceImpl(SectionRepository sectionRepository,
                              ProfessorRepository professorRepository,
                              CourseRepository courseRepository) {
        this.sectionRepository = sectionRepository;
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<SectionEntity> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public void createSection(SectionForm form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
        
        SectionEntity entity = new SectionEntity();
        entity.setCode(form.code());
        entity.setNom(form.nom());
        entity.setProfesseurDirigeant(professorRepository.findByNumeroMatricule(form.professeurDirigeantId()).get());
        entity.setCourseEntities(form.coursId().stream().map(ids->courseRepository.findById(ids).get()).collect(Collectors.toSet()));
        sectionRepository.save(entity);
    }

    @Override
    public Optional<SectionEntity> getSectionByCode(String code) {
        return sectionRepository.findById(code);
    }

    @Override
    public void updateSection(String code, SectionForm form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");

        SectionEntity entity = sectionRepository.findById(code).orElseThrow(()->new NotFoundException("Pas trouvé"));
        entity.setCode(form.code());
        entity.setNom(form.nom());
        entity.setProfesseurDirigeant(professorRepository.findByNumeroMatricule(form.professeurDirigeantId()).get());
        entity.setCourseEntities(form.coursId().stream().map(ids->courseRepository.findById(ids).get()).collect(Collectors.toSet()));
        sectionRepository.save(entity);
    }

    @Override
    public void deleteSection(String code) {
        sectionRepository.deleteById(code);
    }

    @Override
    public List<StudentEntity> getStudentsBySection(String id) {
        return sectionRepository.findStudentsBySection(id);
    }
}
