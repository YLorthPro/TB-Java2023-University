package be.bstorm.exouniversite.dal.repositories;

import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.dal.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<UserEntity> findByLogin(String login);

}