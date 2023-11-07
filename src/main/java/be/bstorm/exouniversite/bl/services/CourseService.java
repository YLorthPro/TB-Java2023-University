package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.CourseEntity;
import be.bstorm.exouniversite.pl.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseEntity> getAllCourse();
    void createCourse(Course form);
    Optional<CourseEntity> getCourseByMnemonique(String mnemonique);
    void updateCourse(String mnemonique, Course course);
    void deleteCourse(String mnemonique);
}
