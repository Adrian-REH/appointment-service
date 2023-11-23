package app.appointment.service.forms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormResponse {
    private String data;
    private String idMedical;
    private String idPatient;

}
