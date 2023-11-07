package be.bstorm.exouniversite.dal.repositories;

import be.bstorm.exouniversite.dal.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}