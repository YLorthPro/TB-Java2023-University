package be.bstorm.exouniversite.dal.repositories;

import be.bstorm.exouniversite.dal.models.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    @Query("SELECT c FROM CourseEntity c JOIN c.studentEntities st WHERE st.numeroMatricule = :studentMatricule")
    List<CourseEntity> findAllByStudentId(@Param("studentMatricule") String studentMatricule);


}