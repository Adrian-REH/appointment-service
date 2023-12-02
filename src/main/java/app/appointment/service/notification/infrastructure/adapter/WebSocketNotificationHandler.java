package app.appointment.service.notification.infrastructure.adapter;

import app.appointment.service.notification.domain.port.NotificationRepository;
import app.appointment.service.notification.infrastructure.adapter.driver.MongoNotificationRepository;
import app.appointment.service.notification.infrastructure.adapter.driver.entity.NotificationSessionEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketNotificationHandler implements NotificationRepository {

    @Autowired
    private MongoNotificationRepository notificationRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;



    @Override
    public void sendNotification(String destination, String message) {
        List<NotificationSessionEntity> sessions  = notificationRepository.findAllByDestination(destination);

        for (NotificationSessionEntity session : sessions) {
            String sessionId = session.getSessionId();

            messagingTemplate.convertAndSendToUser(
                    sessionId,
                    "/queue/messages",
                    message,
                    createHeaders(sessionId)
            );        }
    }
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
