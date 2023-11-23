package app.appointment.service.forms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FormRequest {
    @NotBlank
    private String data;
    @NotBlank
    private String idMedical;
    @NotBlank
    private String idPatient;

}
