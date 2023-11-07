package be.bstorm.exouniversite.pl.models;

import be.bstorm.exouniversite.dal.models.entities.AddressEntity;

public record Address(
        Long id,
        String numero,
        String rue,
        String codePostal,
        String ville
) {
    public static Address fromEntity(AddressEntity entity){
        return new Address(entity.getId(), entity.getNumero(), entity.getRue(), entity.getCodePostal(), entity.getVille());
    }
}
