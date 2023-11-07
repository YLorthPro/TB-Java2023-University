package be.bstorm.exouniversite.pl.models.forms;

import be.bstorm.exouniversite.pl.models.dtos.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SectionForm(
        @NotBlank
        @Size(min = 4)
        String code,
        @NotBlank
        @Size(min = 4)
        String nom,
        @NotBlank
        @Size(min = 4)
        String professeurDirigeantId,
        @NotNull
        List<String> coursId
) {
}
