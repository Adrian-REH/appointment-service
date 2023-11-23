package app.appointment.service.medical.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRequest {

    @NotBlank
    private String nameLast;
    @NotBlank
    private String name;
    @NotBlank
    private String dni;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "No es una dirección de correo electrónico válida")
    private String email;
    @NotBlank
    @Pattern(regexp = "^(::)$", message = "Not exist the column")
    private String direction;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String tuition;

    private String phone;
    private String profession;
    private String img;
    private String tokenNot;
    private String hourOn;

}
