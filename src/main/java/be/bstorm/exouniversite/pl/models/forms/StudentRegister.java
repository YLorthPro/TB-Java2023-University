package be.bstorm.exouniversite.pl.models.forms;

import be.bstorm.exouniversite.dal.models.entities.StudentEntity;
import be.bstorm.exouniversite.pl.models.dtos.Address;
import be.bstorm.exouniversite.pl.models.dtos.Course;
import be.bstorm.exouniversite.pl.models.dtos.Section;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record StudentRegister(
        @NotBlank
        @Size(min = 4)
        String numeroMatricule,
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
        List<String> coursSuivisId,
        @NotBlank
        @Size(min = 4)
        String filiereChoisieId
) {
}
