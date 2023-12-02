package app.appointment.service.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationDto {
    private String token;
    private String patient;
    private String medical;
    private String dateAppointment;
    private Boolean isDeleteAppointment;
}
