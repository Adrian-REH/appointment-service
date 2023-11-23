package app.appointment.service.patient.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    @NotBlank
    private String username;
    @NotBlank
    private String nameLast;
    @NotBlank
    private String password;
    @NotBlank
    private String dni;
    private String phone;
    @NotBlank
    private String email;
    private String direction;
    private String img;
    private String tokenNot;
}