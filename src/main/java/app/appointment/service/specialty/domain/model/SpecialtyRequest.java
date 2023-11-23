package app.appointment.service.specialty.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyRequest {
    private String title;
    private String comment;
    private String idMedical;
    private String offer;
    private String price;
}
