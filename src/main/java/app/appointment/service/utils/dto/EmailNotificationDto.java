package app.appointment.service.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationDto {
    private String email;
    private String patient;
    private String medical;
    private String username;
    private String dateAppointment;
    private Boolean isDeleteAppointment;
    private String twoFactorCode;
    private LocalDateTime twoFactorCreatedCode;
    private LocalDateTime twoFactorExpiryCode;
}
