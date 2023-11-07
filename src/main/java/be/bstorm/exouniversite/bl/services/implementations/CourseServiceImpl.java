package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.services.CourseService;
import be.bstorm.exouniversite.dal.models.entities.CourseEntity;
import be.bstorm.exouniversite.dal.repositories.CourseRepository;
import be.bstorm.exouniversite.pl.models.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseEntity> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public void createCourse(Course form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public Optional<CourseEntity> getCourseByMnemonique(String mnemonique) {
        return courseRepository.findById(mnemonique);
    }

    @Override
    public void updateCourse(String mnemonique, Course form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public void deleteCourse(String mnemonique) {
        courseRepository.deleteById(mnemonique);
    }
}
