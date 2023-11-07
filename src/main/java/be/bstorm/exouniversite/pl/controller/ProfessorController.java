package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.ProfessorService;
import be.bstorm.exouniversite.pl.models.dtos.Professor;
import be.bstorm.exouniversite.pl.models.forms.ProfessorRegister;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors().stream().map(Professor::fromEntity).toList());
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createProfessor(@RequestBody @Valid ProfessorRegister professor) {
        professorService.createProfessor(professor);
    }
    
    @GetMapping("/{numeroMatricule}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Professor> getProfessorByNumeroMatricule(@PathVariable String numeroMatricule) {
        return ResponseEntity.ok(Professor.fromEntity(professorService.getProfessorByNumeroMatricule(numeroMatricule).orElseThrow(()->new NotFoundException("Pas trouvé"))));
    }
    
    @PutMapping("/{numeroMatricule}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProfessor(@PathVariable String numeroMatricule, @RequestBody @Valid ProfessorRegister professor) {
        professorService.updateProfessor(numeroMatricule, professor);
    }
    
    @DeleteMapping("/{numeroMatricule}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProfessor(@PathVariable String numeroMatricule) {
        professorService.deleteProfessor(numeroMatricule);
    }
}

