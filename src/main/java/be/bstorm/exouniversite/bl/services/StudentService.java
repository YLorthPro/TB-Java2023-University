package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.pl.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentEntity> getAllStudents();
    void createStudent(Student form);
    Optional<StudentEntity> getStudentById(Long id);
    void updateStudent(Long id, Student form);
    void deleteStudent(Long id);
}
