package be.bstorm.exouniversite.pl.models.dtos;

import be.bstorm.exouniversite.dal.models.entities.ProfessorEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Professor(
        Long id,
        String numeroMatricule,
        String titre,
        String nom,
        String prenom
) {
    public static Professor fromEntity(ProfessorEntity entity){
        return new Professor(entity.getId(), entity.getNumeroMatricule(), entity.getTitre(), entity.getNom(), entity.getPrenom());
    }
}
