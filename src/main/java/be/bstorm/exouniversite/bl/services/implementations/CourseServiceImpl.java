package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.CourseService;
import be.bstorm.exouniversite.dal.models.entities.CourseEntity;
import be.bstorm.exouniversite.dal.repositories.CourseRepository;
import be.bstorm.exouniversite.dal.repositories.ProfessorRepository;
import be.bstorm.exouniversite.dal.repositories.SectionRepository;
import be.bstorm.exouniversite.pl.models.forms.CourseForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final ProfessorRepository professorRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             SectionRepository sectionRepository,
                             ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.sectionRepository = sectionRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<CourseEntity> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public void createCourse(CourseForm form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
        
        CourseEntity entity = new CourseEntity();
        entity.setMnemonique(form.mnemonique());
        entity.setIntitule(form.intitule());
        entity.setResume(form.resume());
        entity.setSectionEntity(sectionRepository.findById(form.sectionId()).orElseThrow(()-> new NotFoundException("Pas trouvé")));
        entity.setProfesseurTitulaire(professorRepository.findById(form.professeurTitulaireId()).orElseThrow(()-> new NotFoundException("Pas trouvé")));
        courseRepository.save(entity);
    }

    @Override
    public Optional<CourseEntity> getCourseByMnemonique(String mnemonique) {
        return courseRepository.findById(mnemonique);
    }

    @Override
    public void updateCourse(String mnemonique, CourseForm form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");

        CourseEntity entity = courseRepository.findById(mnemonique).orElseThrow(()-> new NotFoundException("Pas trouvé"));
        entity.setMnemonique(form.mnemonique());
        entity.setIntitule(form.intitule());
        entity.setResume(form.resume());
        entity.setSectionEntity(sectionRepository.findById(form.sectionId()).orElseThrow(()-> new NotFoundException("Pas trouvé")));
        entity.setProfesseurTitulaire(professorRepository.findById(form.professeurTitulaireId()).orElseThrow(()-> new NotFoundException("Pas trouvé")));
        courseRepository.save(entity);
    }

    @Override
    public void deleteCourse(String mnemonique) {
        courseRepository.deleteById(mnemonique);
    }

    @Override
    public List<CourseEntity> getAllCourseByStudent(String id) {
        return courseRepository.findAllByStudentId(id);
    }
}
