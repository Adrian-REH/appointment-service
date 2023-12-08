package app.appointment.service.medical.application;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.utils.dto.EmailNotificationDto;
import app.appointment.service.utils.notification.AsyncNotifications;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMedical {

    private final MedicalRepository medicalRepository;
    private final AsyncNotifications asyncNotifications;
    public MedicalResponse execute(MedicalRequest request) {
        var  response = medicalRepository.createNewMedical(request);

        asyncNotifications.emailNotifyNewAccount(EmailNotificationDto.builder()
                        .medical(request.getName())
                        .patient("")
                        .username(request.getUsername())
                        .email(request.getEmail())
                .build());

        return response;
    }
}
