package be.bstorm.exouniversite.dal.models.entities;

import be.bstorm.exouniversite.dal.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="Student")
@Setter
public final class StudentEntity extends UserEntity {
    @Column(nullable = false, unique = true)
    @Getter
    private String numeroMatricule;
    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    protected Set<CourseEntity> coursSuivis;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    protected SectionEntity filiereChoisie;

    public Set<CourseEntity> getCoursSuivis() {
        return new HashSet<>(coursSuivis);
    }

    public SectionEntity getFiliereChoisie() {
        return filiereChoisie;
    }

    @Override
    public void setUserRole() {
        this.userRole = UserRole.STUDENT;
    }
}
