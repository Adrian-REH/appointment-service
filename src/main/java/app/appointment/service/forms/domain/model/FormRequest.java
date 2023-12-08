package app.appointment.service.forms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FormRequest {
    @NotBlank
    private String data;
    @NotBlank
    private String usernameMedical;
    @NotBlank
    private String usernamePatient;

}
