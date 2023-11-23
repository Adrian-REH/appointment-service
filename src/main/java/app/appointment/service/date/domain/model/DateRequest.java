package app.appointment.service.date.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateRequest {
    @NotBlank
    private String idMedical;
    @NotBlank
    @Pattern(regexp = "^(?:[0-1]?[0-9]|2[0-3]):[0-5][0-9] de (?:[0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de hora no es valido")
    private String monday;
    @NotBlank
    @Pattern(regexp = "^(?:[0-1]?[0-9]|2[0-3]):[0-5][0-9] de (?:[0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de hora no es valido")
    private String tuesday;
    @NotBlank
    @Pattern(regexp = "^(?:[0-1]?[0-9]|2[0-3]):[0-5][0-9] de (?:[0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de hora no es valido")
    private String wednesday;
    @NotBlank
    @Pattern(regexp = "^(?:[0-1]?[0-9]|2[0-3]):[0-5][0-9] de (?:[0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de hora no es valido")
    private String thursday;
    @NotBlank
    @Pattern(regexp = "^(?:[0-1]?[0-9]|2[0-3]):[0-5][0-9] de (?:[0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de hora no es valido")
    private String friday;
    @NotBlank
    @Pattern(regexp = "^(?:[0-1]?[0-9]|2[0-3]):[0-5][0-9] de (?:[0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de hora no es valido")
    private String saturday;
    @NotBlank
    @Pattern(regexp = "^(?:[0-1]?[0-9]|2[0-3]):[0-5][0-9] de (?:[0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El formato de hora no es valido")
    private String sunday;

}
