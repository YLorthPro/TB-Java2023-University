package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.dal.models.entities.UserEntity;
import be.bstorm.exouniversite.dal.repositories.ProfessorRepository;
import be.bstorm.exouniversite.dal.repositories.StudentRepository;
import be.bstorm.exouniversite.pl.config.security.JWTProvider;
import be.bstorm.exouniversite.pl.models.dtos.Auth;
import be.bstorm.exouniversite.pl.models.forms.LoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JWTProvider jwtProvider,  StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public Auth login(LoginForm form) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.login(),form.password()));

        UserEntity user;

        if(professorRepository.existsByLogin(form.login()))
            user = professorRepository.findByLogin(form.login()).get();

        else
            user = studentRepository.findByLogin(form.login())
                    .orElseThrow(() -> new UsernameNotFoundException("Pas trouvé"));

        String token = jwtProvider.generateToken(user.getUsername(), List.copyOf(user.getUserRoles()));

        return new Auth(token, user.getLogin(), user.getUserRoles());
    }
}
