package be.bstorm.exouniversite.dal.repositories;

import be.bstorm.exouniversite.dal.models.entities.ProfessorEntity;
import be.bstorm.exouniversite.dal.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
    boolean existsByLogin(String login);
    Optional<ProfessorEntity> findByNumeroMatricule(String numeroMatricule);
    void deleteByNumeroMatricule(String numeroMatricule);
}