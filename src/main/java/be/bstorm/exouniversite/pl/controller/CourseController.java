package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.CourseService;
import be.bstorm.exouniversite.pl.models.dtos.Course;
import be.bstorm.exouniversite.pl.models.forms.CourseForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourse() {
        return ResponseEntity.ok(courseService.getAllCourse().stream().map(Course::fromEntity).toList());
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createCourse(@RequestBody @Valid CourseForm course) {
        courseService.createCourse(course);
    }
    
    @GetMapping("/{mnemonique}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Course> getCourseByMnemonique(@PathVariable String mnemonique) {
        return ResponseEntity.ok(Course.fromEntity(courseService.getCourseByMnemonique(mnemonique).orElseThrow(() -> new NotFoundException("Pas trouvé"))));
    }
    
    @PutMapping("/{mnemonique}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCourse(@PathVariable String mnemonique, @RequestBody @Valid CourseForm course) {
        courseService.updateCourse(mnemonique, course);
    }
    
    @DeleteMapping("/{mnemonique}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCourse(@PathVariable String mnemonique) {
        courseService.deleteCourse(mnemonique);
    }

    @GetMapping("/student/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
    public ResponseEntity<List<Course>> getAllCourseByStudent(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getAllCourseByStudent(id).stream().map(Course::fromEntity).toList());
    }
}
