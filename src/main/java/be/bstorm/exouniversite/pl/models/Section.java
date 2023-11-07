package be.bstorm.exouniversite.pl.models;

import be.bstorm.exouniversite.dal.models.entities.SectionEntity;

public record Section(
        String code,
        String nom,
        Professor professeurDirigeant
) {
    public static Section fromEntity(SectionEntity entity){
        return new Section(entity.getCode(), entity.getNom(), Professor.fromEntity(entity.getProfesseurDirigeant()));
    }
}
