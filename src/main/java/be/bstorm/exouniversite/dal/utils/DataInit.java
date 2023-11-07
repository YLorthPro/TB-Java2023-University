package be.bstorm.exouniversite.dal.utils;

import be.bstorm.exouniversite.dal.models.entities.*;
import be.bstorm.exouniversite.dal.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInit implements InitializingBean {
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInit(StudentRepository studentRepository, ProfessorRepository professorRepository, CourseRepository courseRepository, SectionRepository sectionRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
        this.sectionRepository = sectionRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() {
        // Ajouter des adresses
        AddressEntity address1 = new AddressEntity();
        address1.setNumero("123");
        address1.setRue("Rue de la Poste");
        address1.setCodePostal("12345");
        address1.setVille("Ville 1");
        addressRepository.save(address1);

        AddressEntity address2 = new AddressEntity();
        address2.setNumero("456");
        address2.setRue("Rue Principale");
        address2.setCodePostal("67890");
        address2.setVille("Ville 2");
        addressRepository.save(address2);

        // Ajouter des professeurs
        ProfessorEntity professor1 = new ProfessorEntity();
        professor1.setNom("Professeur1");
        professor1.setPrenom("PrenomProf1");
        professor1.setLogin("professeur1");
        professor1.setPassword(passwordEncoder.encode("Test1234="));
        professor1.setUserRoles(true);
        professor1.setNumeroMatricule("MatriculeProf1");
        professor1.setTitre("TitreProf1");
        professor1.setAddress(Set.of(address1));
        professorRepository.save(professor1);

        ProfessorEntity professor2 = new ProfessorEntity();
        professor2.setNom("Professeur2");
        professor2.setPrenom("PrenomProf2");
        professor2.setLogin("professeur2");
        professor2.setPassword(passwordEncoder.encode("Test1234="));
        professor2.setUserRoles(false);
        professor2.setNumeroMatricule("MatriculeProf2");
        professor2.setTitre("TitreProf2");
        professor2.setAddress(Set.of (address2));
        professorRepository.save(professor2);

        // Ajouter des filières
        SectionEntity section1 = new SectionEntity();
        section1.setCode("Section1");
        section1.setNom("NomSection1");
        section1.setProfesseurDirigeant(professor1);
        sectionRepository.save(section1);

        SectionEntity section2 = new SectionEntity();
        section2.setCode("Section2");
        section2.setNom("NomSection2");
        section2.setProfesseurDirigeant(professor2);
        sectionRepository.save(section2);

        // Ajouter des cours
        CourseEntity course1 = new CourseEntity();
        course1.setMnemonique("Cours1");
        course1.setIntitule("IntituleCours1");
        course1.setResume("ResumeCours1");
        course1.setProfesseurTitulaire(professor1);
        course1.setSectionEntity(section1);
        courseRepository.save(course1);

        CourseEntity course2 = new CourseEntity();
        course2.setMnemonique("Cours2");
        course2.setIntitule("IntituleCours2");
        course2.setResume("ResumeCours2");
        course2.setProfesseurTitulaire(professor2);
        course2.setSectionEntity(section2);
        courseRepository.save(course2);

        // Ajouter des étudiants
        StudentEntity student1 = new StudentEntity();
        student1.setNom("Nom1");
        student1.setPrenom("Prenom1");
        student1.setLogin("etudiant1");
        student1.setPassword(passwordEncoder.encode("Test1234="));
        student1.setUserRoles();
        student1.setNumeroMatricule("Matricule1");
        student1.setAddress(Set.of(address1));
        student1.setFiliereChoisie(section1);
        student1.setCoursSuivis(Set.of(course1));
        studentRepository.save(student1);

        StudentEntity student2 = new StudentEntity();
        student2.setNom("Nom2");
        student2.setPrenom("Prenom2");
        student2.setLogin("etudiant2");
        student2.setPassword(passwordEncoder.encode("Test1234="));
        student2.setUserRoles();
        student2.setNumeroMatricule("Matricule2");
        student2.setAddress(Set.of(address2));
        student2.setFiliereChoisie(section2);
        student2.setCoursSuivis(Set.of(course2));
        studentRepository.save(student2);


    }
}

