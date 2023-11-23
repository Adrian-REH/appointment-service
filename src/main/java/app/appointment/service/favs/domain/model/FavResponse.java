package app.appointment.service.favs.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavResponse {
    private String medical;
    private String patient;
}
