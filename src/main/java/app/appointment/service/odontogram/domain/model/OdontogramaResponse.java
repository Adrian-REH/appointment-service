package app.appointment.service.odontogram.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OdontogramaResponse {
    private String id;
    private List<DataEntryResponse> data;
    private String medical;
    private String patient;

}
