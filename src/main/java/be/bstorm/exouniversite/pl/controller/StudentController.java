package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.StudentService;
import be.bstorm.exouniversite.pl.models.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents().stream().map(Student::fromEntity).toList();
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Student getStudentById(@PathVariable Long id) {
        return Student.fromEntity(studentService.getStudentById(id).orElseThrow(() -> new NotFoundException("Pas trouvé")));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
