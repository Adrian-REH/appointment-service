package app.appointment.service.odontogram.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OdontogramaRequest {
    @NotEmpty
    private List<DataEntryRequest> data;
    @NotBlank
    private String medical;
    @NotBlank
    private String patient;

}
