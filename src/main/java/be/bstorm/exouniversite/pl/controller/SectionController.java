package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.SectionService;
import be.bstorm.exouniversite.pl.models.dtos.Section;
import be.bstorm.exouniversite.pl.models.dtos.Student;
import be.bstorm.exouniversite.pl.models.forms.SectionForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {
    private SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Section>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections().stream().map(Section::fromEntity).toList());
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createSection(@RequestBody @Valid SectionForm section) {
        sectionService.createSection(section);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Section> getSectionByCode(@PathVariable String code) {
        return ResponseEntity.ok(Section.fromEntity(sectionService.getSectionByCode(code).orElseThrow(()->new NotFoundException("Pas trouvé"))));
    }
    
    @PutMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateSection(@PathVariable String code, @RequestBody @Valid SectionForm section) {
        sectionService.updateSection(code, section);
    }
    
    @DeleteMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSection(@PathVariable String code) {
        sectionService.deleteSection(code);
    }
    
    @GetMapping("/{id}/students")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<List<Student>> getAllStudentsBySection(@PathVariable String id){
        return ResponseEntity.ok(sectionService.getStudentsBySection(id).stream().map(Student::fromEntity).toList());
    }
}
