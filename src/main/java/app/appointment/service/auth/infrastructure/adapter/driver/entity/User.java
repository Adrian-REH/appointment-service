package app.appointment.service.auth.infrastructure.adapter.driver.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  implements Serializable{

    @Id
    private String id;
    private String username;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

    private Role roles;

    public User(String username, String nombre, String apellido, String email, String password, Role roles) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}