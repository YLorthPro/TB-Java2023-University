package be.bstorm.exouniversite.pl.models.dtos;

import be.bstorm.exouniversite.dal.models.entities.CourseEntity;

public record Course(
        String mnemonique,
        String intitule,
        String resume,
        Professor professeurTitulaire
) {
    public static Course fromEntity(CourseEntity entity){
        return new Course(entity.getMnemonique(), entity.getIntitule(), entity.getResume(), Professor.fromEntity(entity.getProfesseurTitulaire()));
    }
}
