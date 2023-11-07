package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.ProfessorService;
import be.bstorm.exouniversite.pl.models.Professor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    
    @GetMapping("/all")
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors().stream().map(Professor::fromEntity).toList();
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createProfessor(@RequestBody Professor professor) {
        professorService.createProfessor(professor);
    }
    
    @GetMapping("/{numeroMatricule}")
    public Professor getProfessorByNumeroMatricule(@PathVariable String numeroMatricule) {
        return Professor.fromEntity(professorService.getProfessorByNumeroMatricule(numeroMatricule).orElseThrow(()->new NotFoundException("Pas trouvé")));
    }
    
    @PutMapping("/{numeroMatricule}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProfessor(@PathVariable String numeroMatricule, @RequestBody Professor professor) {
        professorService.updateProfessor(numeroMatricule, professor);
    }
    
    @DeleteMapping("/{numeroMatricule}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProfessor(@PathVariable String numeroMatricule) {
        professorService.deleteProfessor(numeroMatricule);
    }
}

