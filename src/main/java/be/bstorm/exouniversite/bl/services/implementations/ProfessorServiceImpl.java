package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.bl.models.NotFoundException;
import be.bstorm.exouniversite.bl.services.ProfessorService;
import be.bstorm.exouniversite.dal.models.entities.AddressEntity;
import be.bstorm.exouniversite.dal.models.entities.ProfessorEntity;
import be.bstorm.exouniversite.dal.repositories.AddressRepository;
import be.bstorm.exouniversite.dal.repositories.CourseRepository;
import be.bstorm.exouniversite.dal.repositories.ProfessorRepository;
import be.bstorm.exouniversite.pl.models.forms.ProfessorRegister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    
    private final ProfessorRepository professorRepository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorServiceImpl(ProfessorRepository professorRepository,
                                AddressRepository addressRepository,
                                CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.professorRepository = professorRepository;
        this.addressRepository = addressRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ProfessorEntity> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public void createProfessor(ProfessorRegister form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");

        ProfessorEntity entity = new ProfessorEntity();
        entity.setNom(form.nom());
        entity.setPrenom(form.prenom());
        entity.setTitre(form.titre());
        entity.setPassword(passwordEncoder.encode(form.password()));
        entity.setNumeroMatricule(form.numeroMatricule());
        entity.setLogin(form.login());
        entity.setUserRoles(form.isAdmin());
        entity.setCoursEnseignes(form.coursEnseignesId().stream().map(id -> courseRepository.findById(id).get()).toList());

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

        professorRepository.save(entity);
    }

    @Override
    public Optional<ProfessorEntity> getProfessorByNumeroMatricule(String numeroMatricule) {
        return professorRepository.findByNumeroMatricule(numeroMatricule);
    }

    @Override
    public void updateProfessor(String numeroMatricule, ProfessorRegister form) {
        if(form == null)
            throw new IllegalArgumentException("Form ne peut être null");

        ProfessorEntity entity = professorRepository.findByNumeroMatricule(numeroMatricule).orElseThrow(()->new NotFoundException("Pas trouvé"));
        entity.setNom(form.nom());
        entity.setPrenom(form.prenom());
        entity.setTitre(form.titre());
        entity.setPassword(passwordEncoder.encode(form.password()));
        entity.setNumeroMatricule(form.numeroMatricule());
        entity.setLogin(form.login());
        entity.setUserRoles(form.isAdmin());
        entity.setCoursEnseignes(form.coursEnseignesId().stream().map(id -> courseRepository.findById(id).get()).toList());

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

        professorRepository.save(entity);
    }

    @Override
    public void deleteProfessor(String numeroMatricule) {
        professorRepository.deleteByNumeroMatricule(numeroMatricule);
    }
}
