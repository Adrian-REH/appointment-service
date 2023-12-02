package app.appointment.service.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationDto {
    private String email;
    private String patient;
    private String medical;
    private String dateAppointment;
    private Boolean isDeleteAppointment;
}
