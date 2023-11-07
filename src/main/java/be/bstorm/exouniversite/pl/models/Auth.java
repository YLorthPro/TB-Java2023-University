package be.bstorm.exouniversite.pl.models;

import be.bstorm.exouniversite.dal.models.enums.UserRole;

import java.util.Set;

public record Auth(
        String login,
        String token,
        Set<UserRole>roles
) {
}
