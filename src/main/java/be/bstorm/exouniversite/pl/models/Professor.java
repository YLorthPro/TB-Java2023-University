package be.bstorm.exouniversite.pl.models;

import be.bstorm.exouniversite.dal.models.entities.ProfessorEntity;
import be.bstorm.exouniversite.dal.models.entities.StudentEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Professor(
        Long id,
        String numeroMatricule,
        String titre,
        String nom,
        String prenom,
        Set<Address> addresses,
        List<Course> coursEnseignes
) {
    public static Professor fromEntity(ProfessorEntity entity){
        return new Professor(entity.getId(), entity.getNumeroMatricule(), entity.getTitre(), entity.getNom(), entity.getPrenom(), entity.getAddress().stream().map(Address::fromEntity).collect(Collectors.toSet()), entity.getCoursEnseignes().stream().map(Course::fromEntity).toList());
    }
}
