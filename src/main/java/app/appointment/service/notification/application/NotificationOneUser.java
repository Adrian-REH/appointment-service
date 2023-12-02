package app.appointment.service.notification.application;

import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.notification.domain.port.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationOneUser {

    private final NotificationRepository notificationRepository;

    public void execute(String destination, String message) {
        notificationRepository.sendNotification(destination, message);
    }
}
