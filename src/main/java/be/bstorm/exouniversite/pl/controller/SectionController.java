package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.SectionService;
import be.bstorm.exouniversite.pl.models.Section;
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
    public List<Section> getAllSections() {
        return sectionService.getAllSections().stream().map(Section::fromEntity).toList();
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createSection(@RequestBody Section section) {
        sectionService.createSection(section);
    }
    
    @GetMapping("/{code}")
    public Section getSectionByCode(@PathVariable String code) {
        return Section.fromEntity(sectionService.getSectionByCode(code).orElseThrow(()->new NotFoundException("Pas trouvé")));
    }
    
    @PutMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateSection(@PathVariable String code, @RequestBody Section section) {
        sectionService.updateSection(code, section);
    }
    
    @DeleteMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSection(@PathVariable String code) {
        sectionService.deleteSection(code);
    }
}
