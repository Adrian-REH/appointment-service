package app.appointment.service.odontogram.infrastructure.adapter.driver.entity;

import app.appointment.service.odontogram.domain.model.DataEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "odontogram")
public class OdontogramaEntity {
    @Id
    private String id;
    private List<DataEntry> data;
    @Field(name = "id_medical")
    private String idMedical;
    @Field(name = "id_patient")
    private String idPatient;

}
