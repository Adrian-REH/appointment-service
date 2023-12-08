package app.appointment.service.favs.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavResponse {
    private String usernameMedical;
    private String usernamePatient;
}
