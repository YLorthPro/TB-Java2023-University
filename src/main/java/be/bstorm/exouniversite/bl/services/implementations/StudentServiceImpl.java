package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.services.StudentService;
import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.dal.repositories.StudentRepository;
import be.bstorm.exouniversite.pl.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void createStudent(Student form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public Optional<StudentEntity> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void updateStudent(Long id, Student form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
