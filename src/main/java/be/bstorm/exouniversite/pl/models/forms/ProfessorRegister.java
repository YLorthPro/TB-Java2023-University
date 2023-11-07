package be.bstorm.exouniversite.pl.models.forms;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Setter;

import java.util.List;

public record ProfessorRegister(
        @NotBlank
        @Size(min = 4)
        String numeroMatricule,
        @NotBlank
        @Size(min = 4)
        String titre,
        @NotBlank
        @Size(min = 2)
        String nom,
        @NotBlank
        @Size(min = 3)
        String prenom,
        @NotBlank
        @Size(min = 4)
        String login,
        @NotBlank
        @Size(min = 4)
        String password,
        @NotBlank
        String numero,
        @NotBlank
        @Size(min = 4)
        String rue,
        @NotBlank
        @Size(min = 4, max = 8)
        String codePostal,
        @NotBlank
        @Size(min = 4)
        String ville,
        @NotNull
        List<String> coursEnseignesId,
        boolean isAdmin
) {
}
