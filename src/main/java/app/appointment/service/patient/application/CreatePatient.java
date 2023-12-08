package app.appointment.service.patient.application;

import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.dto.EmailNotificationDto;
import app.appointment.service.utils.notification.AsyncNotifications;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePatient {
    private final AsyncNotifications asyncNotifications;

    private final PatientRepository patientRepository;
    public PatientResponse execute(PatientRequest request) {

        var response =patientRepository.createNewPatient(request);
        asyncNotifications.emailNotifyNewAccount(EmailNotificationDto.builder()
                .patient(request.getName())
                        .medical("")
                .username(request.getUsername())
                .email(request.getEmail())
                .build());
        return response;
    }
}
