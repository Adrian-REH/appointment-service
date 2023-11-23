package app.appointment.service.auth.domain.model;

import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Datos que se almacenan de un usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {



    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String apellido;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;


    public User getRegisterFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setNombre(nombre);
        user.setApellido(apellido);

        return user;
    }

}