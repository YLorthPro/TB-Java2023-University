package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.StudentService;
import be.bstorm.exouniversite.dal.models.entities.AddressEntity;
import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.dal.repositories.AddressRepository;
import be.bstorm.exouniversite.dal.repositories.CourseRepository;
import be.bstorm.exouniversite.dal.repositories.SectionRepository;
import be.bstorm.exouniversite.dal.repositories.StudentRepository;
import be.bstorm.exouniversite.pl.models.forms.StudentRegister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository,
                              AddressRepository addressRepository,
                              SectionRepository sectionRepository,
                              CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void createStudent(StudentRegister form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");

        StudentEntity entity = new StudentEntity();
        entity.setNom(form.nom());
        entity.setPrenom(form.prenom());
        entity.setPassword(passwordEncoder.encode(form.password()));
        entity.setNumeroMatricule(form.numeroMatricule());
        entity.setLogin(form.login());
        entity.setUserRoles();
        entity.setFiliereChoisie(sectionRepository.findById(form.filiereChoisieId()).get());
        entity.setCoursSuivis(form.coursSuivisId().stream().map(id->courseRepository.findById(id).get()).collect(Collectors.toSet()));

        if(addressRepository.existsAddressByNumeroAndRueAndCodePostalAndVille(form.numero(),form.rue(),form.codePostal(),form.ville()))
            entity.getAddress().add(addressRepository.findAddressByNumeroAndRueAndCodePostalAndVille(form.numero(),form.rue(),form.codePostal(),form.ville()).get());
        else{
            AddressEntity address = new AddressEntity();
            address.setRue(form.rue());
            address.setNumero(form.numero());
            address.setVille(form.ville());
            address.setCodePostal(form.codePostal());
            address = addressRepository.save(address);
            entity.getAddress().add(address);
        }

        studentRepository.save(entity);
    }

    @Override
    public Optional<StudentEntity> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void updateStudent(Long id, StudentRegister form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");

        StudentEntity entity = studentRepository.findById(id).orElseThrow(()->new NotFoundException("Pas trouvé"));
        entity.setNom(form.nom());
        entity.setPrenom(form.prenom());
        entity.setPassword(passwordEncoder.encode(form.password()));
        entity.setNumeroMatricule(form.numeroMatricule());
        entity.setLogin(form.login());
        entity.setUserRoles();
        entity.setFiliereChoisie(sectionRepository.findById(form.filiereChoisieId()).get());
        entity.setCoursSuivis(form.coursSuivisId().stream().map(ids->courseRepository.findById(ids).get()).collect(Collectors.toSet()));

        if(addressRepository.existsAddressByNumeroAndRueAndCodePostalAndVille(form.numero(),form.rue(),form.codePostal(),form.ville()))
            entity.getAddress().add(addressRepository.findAddressByNumeroAndRueAndCodePostalAndVille(form.numero(),form.rue(),form.codePostal(),form.ville()).get());
        else{
            AddressEntity address = new AddressEntity();
            address.setRue(form.rue());
            address.setNumero(form.numero());
            address.setVille(form.ville());
            address.setCodePostal(form.codePostal());
            address = addressRepository.save(address);
            entity.getAddress().add(address);
        }

        studentRepository.save(entity);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
