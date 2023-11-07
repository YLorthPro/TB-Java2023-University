package be.bstorm.exouniversite.bl.services.implementations;

import be.bstorm.exouniversite.dal.repositories.ProfessorRepository;
import be.bstorm.exouniversite.dal.repositories.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    public UserDetailsServiceImpl(ProfessorRepository professorRepository,
                                  StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(professorRepository.existsByLogin(username))
            return professorRepository.findByLogin(username).get();
        
        else
            return studentRepository.findByLogin(username) 
                    .orElseThrow(() -> new UsernameNotFoundException("Pas trouvé"));
    }
}
