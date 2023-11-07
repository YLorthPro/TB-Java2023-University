package be.bstorm.exouniversite.pl.models.dtos;

import be.bstorm.exouniversite.dal.models.enums.UserRole;

import java.util.Set;

public record AuthDTO (
        String login,
        String token,
        Set<UserRole> roles
        
){
}
