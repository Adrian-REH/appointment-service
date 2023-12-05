package app.appointment.service.patient.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    private String id;
    private String username;
    private String nameLast;
    private String name;
    private String password;
    private String dni;
    private String phone;
    private String email;
    private String direction;
    private String img;
    private String tokenNot;
}