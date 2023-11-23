package app.appointment.service.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * Formulario para el acceso
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginRequest {

    @NonNull
    private String username;
    @NonNull
    private String password;


}