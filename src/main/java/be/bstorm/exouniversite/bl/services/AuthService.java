package be.bstorm.exouniversite.bl.services;

import be.bstorm.exouniversite.pl.models.dtos.Auth;
import be.bstorm.exouniversite.pl.models.forms.LoginForm;

public interface AuthService {
    Auth login(LoginForm form);
}
