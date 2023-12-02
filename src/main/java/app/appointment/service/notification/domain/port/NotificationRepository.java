package app.appointment.service.notification.domain.port;


import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository {


    void sendNotification(String destination, String message);
}
