package be.bstorm.exouniversite.dal.repositories;

import be.bstorm.exouniversite.dal.models.entities.SectionEntity;
import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, String> {
    @Query("SELECT s FROM StudentEntity s WHERE s.filiereChoisie.code = :sectionId")
    List<StudentEntity> findStudentsBySection(@Param("sectionId") String sectionId);

}