package app.appointment.service.medical.infrastructure.adapter.driver.entity;

import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import app.appointment.service.medical.domain.model.Schedule;
import app.appointment.service.medical.domain.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.formatter.qual.Format;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "medical")
public class MedicalEntity {

    @Id
    private String id;
    private String nameLast;
    private Boolean emailVerified;
    private String name;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String dni;
    private String phone;
    @Indexed(unique = true)
    @Field( "email")
    private String email;
    private String direction;
    private String tuition;
    private String profession;
    private String img;
    private String tokenNot;
    private String hourOn;
    private Role role;
    private String twoFactorCode;
    private Boolean twoFactorCodeUsed;
    private LocalDateTime twoFactorCreatedCode;
    private LocalDateTime twoFactorExpiryCode;
    private List<Schedule> listSchedule;
    private List<Specialty> listSpecialty;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
