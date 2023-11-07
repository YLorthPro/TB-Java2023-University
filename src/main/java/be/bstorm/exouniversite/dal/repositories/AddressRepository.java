package be.bstorm.exouniversite.dal.repositories;

import be.bstorm.exouniversite.dal.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    boolean existsAddressByNumeroAndRueAndCodePostalAndVille(String numero, String rue, String codePostal, String ville);

    Optional<AddressEntity> findAddressByNumeroAndRueAndCodePostalAndVille(String numero, String rue, String codePostal, String ville);
}