package app.appointment.service.laboratory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryResponse {
    private String nameLab;
    private String sedes;
    private String phone;
    private String email;
    private String accredited;
    private String hour_on;
}
