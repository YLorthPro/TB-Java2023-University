package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.CourseService;
import be.bstorm.exouniversite.pl.models.Course;
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
    public List<Course> getAllCourse() {
        return courseService.getAllCourse().stream().map(Course::fromEntity).toList();
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
    }
    
    @GetMapping("/{mnemonique}")
    public Course getCourseByMnemonique(@PathVariable String mnemonique) {
        return Course.fromEntity(courseService.getCourseByMnemonique(mnemonique).orElseThrow(() -> new NotFoundException("Pas trouvé")));
    }
    
    @PutMapping("/{mnemonique}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCourse(@PathVariable String mnemonique, @RequestBody Course course) {
        courseService.updateCourse(mnemonique, course);
    }
    
    @DeleteMapping("/{mnemonique}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCourse(@PathVariable String mnemonique) {
        courseService.deleteCourse(mnemonique);
    }
}
