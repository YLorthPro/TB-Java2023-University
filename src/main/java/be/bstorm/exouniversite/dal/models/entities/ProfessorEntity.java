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
import java.util.Set;

@Entity
@Table(name ="Professor")
@Setter
public final class ProfessorEntity extends UserEntity {
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String numeroMatricule;
    @Column(nullable = false, unique = true)
    @Getter
    private String titre;

    @OneToMany(mappedBy = "professeurTitulaire")
    protected List<CourseEntity> coursEnseignes;

    public List<CourseEntity> getCoursEnseignes() {
        return new ArrayList<>(coursEnseignes);
    }
    
    public void setUserRoles(boolean isAdmin){
        if(isAdmin)
            this.userRoles = Set.of(UserRole.PROFESSOR, UserRole.ADMIN);
        else
            this.userRoles = Set.of(UserRole.PROFESSOR);
    }
}
