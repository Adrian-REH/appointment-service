package app.appointment.service.files.infrastructure.adapter.driver.entity;

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
@Document(collection = "file")
public class FileEntity {
    @Id
    private String id;
    @Field(name = "id_laboratory")
    private String idLaboratory;
    private String studies;
    private String form;
    @Field(name = "id_patient")
    private String idPatient;
    @Field(name = "id_medical")
    private String idMedical;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
