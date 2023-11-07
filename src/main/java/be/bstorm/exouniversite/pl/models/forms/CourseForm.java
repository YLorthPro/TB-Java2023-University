package be.bstorm.exouniversite.pl.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseForm(
    @NotBlank
    @Size(min = 4)
    String mnemonique,
    @NotBlank
    @Size(min = 4)
    String intitule,
    @NotBlank
    @Size(min = 4)
    String resume,
    @NotBlank
    @Size(min = 4)
    String sectionId,
    @NotNull
    Long professeurTitulaireId
) {
}
