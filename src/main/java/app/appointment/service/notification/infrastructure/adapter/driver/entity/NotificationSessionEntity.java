package app.appointment.service.notification.infrastructure.adapter.driver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.socket.WebSocketSession;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "session_notification")
public class NotificationSessionEntity {

    @Id
    private String id;
    private String sessionId;
    private String destination;

}
