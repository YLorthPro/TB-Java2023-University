package be.bstorm.exouniversite.dal.repositories;

import be.bstorm.exouniversite.dal.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}