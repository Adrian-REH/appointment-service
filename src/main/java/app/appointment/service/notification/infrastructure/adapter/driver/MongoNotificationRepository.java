package app.appointment.service.notification.infrastructure.adapter.driver;

import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.notification.infrastructure.adapter.driver.entity.NotificationSessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoNotificationRepository extends MongoRepository<NotificationSessionEntity, String> {


    List<NotificationSessionEntity> findAllByDestination(String destination);
}
