package app.appointment.service.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
@Builder
@AllArgsConstructor
@Data
public class LoginRequestTestDto {
    private String username;
    private String password;

}
