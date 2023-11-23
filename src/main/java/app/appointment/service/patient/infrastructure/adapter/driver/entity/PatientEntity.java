package app.appointment.service.patient.infrastructure.adapter.driver.entity;

import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "patient")
public class PatientEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String nameLast;
    private String password;
    private String dni;
    private String phone;
    @Indexed(unique = true)
    private String email;
    private String direction;
    private String img;
    private String tokenNot;
    private Role role;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    // Otras propiedades, constructores, getters y setters

}
