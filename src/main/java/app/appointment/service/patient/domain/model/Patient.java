package app.appointment.service.patient.domain.model;

import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    private String id;
    private String username;
    private String name;
    private String nameLast;
    private String password;
    private String dni;
    private String phone;
    private String email;
    private String direction;
    private String img;
    private String tokenNot;
    private Role role;
}
