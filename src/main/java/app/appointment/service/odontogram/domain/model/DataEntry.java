package app.appointment.service.odontogram.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataEntry  {
    private String number;
    private String imgTop;
    private String imgBot;

}
