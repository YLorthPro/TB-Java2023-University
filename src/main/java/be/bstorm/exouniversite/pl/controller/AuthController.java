package be.bstorm.exouniversite.pl.controller;

import be.bstorm.exouniversite.bl.services.AuthService;
import be.bstorm.exouniversite.pl.models.dtos.Auth;
import be.bstorm.exouniversite.pl.models.forms.LoginForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Auth login(@RequestBody LoginForm form){
        return authService.login(form);
    }
}
