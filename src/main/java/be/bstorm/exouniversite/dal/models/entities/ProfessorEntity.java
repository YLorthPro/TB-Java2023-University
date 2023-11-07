package be.bstorm.exouniversite.dal.models.entities;

import be.bstorm.exouniversite.dal.models.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Professor")
@Setter
public final class ProfessorEntity extends UserEntity {
    @Column(nullable = false, unique = true)
    @Getter
    private String numeroMatricule;
    @Column(nullable = false, unique = true)
    @Getter
    private String titre;

    @OneToMany(mappedBy = "professeurTitulaire")
    protected List<CourseEntity> coursEnseignes;

    public List<CourseEntity> getCoursEnseignes() {
        return new ArrayList<>(coursEnseignes);
    }
    
    public void setUserRole(){
        this.userRole = UserRole.PROFESSOR;
    }
}
