package be.bstorm.exouniversite.pl.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginForm (
    @NotBlank
    @Size(min = 4)
    String login,
    @NotBlank
    @Size(min = 4)
    String password
){
}
