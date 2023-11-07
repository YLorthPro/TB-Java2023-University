package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.pl.models.forms.StudentRegister;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentEntity> getAllStudents();
    void createStudent(StudentRegister form);
    Optional<StudentEntity> getStudentById(Long id);
    void updateStudent(Long id, StudentRegister form);
    void deleteStudent(Long id);
}
