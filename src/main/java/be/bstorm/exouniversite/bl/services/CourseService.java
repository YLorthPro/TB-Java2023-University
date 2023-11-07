package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.CourseEntity;
import be.bstorm.exouniversite.pl.models.forms.CourseForm;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseEntity> getAllCourse();
    void createCourse(CourseForm form);
    Optional<CourseEntity> getCourseByMnemonique(String mnemonique);
    void updateCourse(String mnemonique, CourseForm course);
    void deleteCourse(String mnemonique);
    List<CourseEntity> getAllCourseByStudent(String id);
}
