package app.appointment.service.patient.domain.model;

import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private String nameLast;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String dni;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "No es una dirección de correo electrónico válida")
    private String email;
    private String phone;
    private String direction;
    private String img;
    private String tokenNot;
}