package app.appointment.service.odontogram.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataEntryResponse {
    private String number;
    private String imgTop;
    private String imgBot;

}
