package app.appointment.service.favs.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavRequest {
    @NotBlank
    private String usernameMedical;
    @NotBlank
    private String usernamePatient;
    private Boolean isActive;

}

