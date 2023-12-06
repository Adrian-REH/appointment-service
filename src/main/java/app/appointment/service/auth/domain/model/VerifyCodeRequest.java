package app.appointment.service.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyCodeRequest {
    @NotNull
    @NotBlank
    private String twoFactorCode;
    @NotNull
    @NotBlank
    private String email;
}
