package be.bstorm.exouniversite.pl.models.dtos;

import be.bstorm.exouniversite.dal.models.entities.StudentEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Student(
        Long id,
        String numeroMatricule,
        String nom,
        String prenom,
        Set<Address> addresses,
        List<Course> coursSuivis,
        Section filiereChoisie
) {
    public static Student fromEntity(StudentEntity entity){
        return new Student(entity.getId(), entity.getNumeroMatricule(), entity.getNom(), entity.getPrenom(), entity.getAddress().stream().map(Address::fromEntity).collect(Collectors.toSet()), entity.getCoursSuivis().stream().map(Course::fromEntity).toList(), Section.fromEntity(entity.getFiliereChoisie()));
    }
}
