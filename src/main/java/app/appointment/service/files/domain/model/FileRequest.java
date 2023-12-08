package app.appointment.service.files.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileRequest {
    private String idLaboratory;
    private String studies;
    private String prescriptions;
    private String idOdontograma;
    private String form;
    @NotBlank
    private String usernamePatient;
    @NotBlank
    private String usernameMedical;

}
