package be.bstorm.exouniversite.dal.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name ="Section")
@Setter
public class SectionEntity {
    @Id
    @Getter
    private String code;
    @Column(nullable = false, unique = true)
    @Getter
    private String nom;
    @ManyToOne
    @Getter
    private ProfessorEntity professeurDirigeant;

    @OneToMany(mappedBy = "sectionEntity", orphanRemoval = true)
    private Set<CourseEntity> courseEntities = new LinkedHashSet<>();

    public Set<CourseEntity> getCourseEntities() {
        return new HashSet<>(courseEntities);
    }
}
