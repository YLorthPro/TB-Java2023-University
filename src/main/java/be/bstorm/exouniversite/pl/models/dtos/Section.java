package be.bstorm.exouniversite.pl.models.dtos;

import be.bstorm.exouniversite.dal.models.entities.SectionEntity;

import java.util.List;

public record Section(
        String code,
        String nom,
        Professor professeurDirigeant,
        List<Course> cours
) {
    public static Section fromEntity(SectionEntity entity){
        return new Section(entity.getCode(), entity.getNom(), Professor.fromEntity(entity.getProfesseurDirigeant()),entity.getCourseEntities().stream().map(Course::fromEntity).toList());
    }
}
