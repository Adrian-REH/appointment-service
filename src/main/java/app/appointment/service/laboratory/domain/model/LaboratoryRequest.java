package app.appointment.service.laboratory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryRequest {
    @NotBlank
    private String nameLab;
    @NotBlank
    private String sedes;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "No es una dirección de correo electrónico válida")
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String accredited;
    @NotBlank
    private String hour_on;
}
