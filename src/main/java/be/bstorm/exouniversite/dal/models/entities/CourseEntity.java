package be.bstorm.exouniversite.dal.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name ="Course")
@Setter
public class CourseEntity {
    @Id
    @Getter
    private String mnemonique;
    @Column(nullable = false, unique = true)
    @Getter
    private String intitule;
    @Getter
    private String resume;
    @ManyToOne
    @Getter
    private ProfessorEntity professeurTitulaire;

    @ManyToOne
    @JoinColumn(name = "section_code")
    private SectionEntity sectionEntity;

    public SectionEntity getSectionEntity() {
        return sectionEntity;
    }

    public void setSectionEntity(SectionEntity sectionEntity) {
        this.sectionEntity = sectionEntity;
    }

    @ManyToMany(mappedBy = "coursSuivis")
    private Collection<StudentEntity> studentEntities;

    public Collection<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(Collection<StudentEntity> studentEntities) {
        this.studentEntities = studentEntities;
    }
}
