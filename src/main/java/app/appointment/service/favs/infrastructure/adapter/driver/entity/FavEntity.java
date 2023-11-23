package app.appointment.service.favs.infrastructure.adapter.driver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "favs")
public class FavEntity {
    @Id
    private String id;
    @Field(name = "is_active")
    private Boolean isActive;
    @Field(name = "id_medical")
    @Indexed(unique = true)
    private String idMedical;
    @Field(name = "id_patient")
    @Indexed(unique = true)
    private String idPatient;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
