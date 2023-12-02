package app.appointment.service.notification.infrastructure.websocket;

import app.appointment.service.auth.domain.model.LoginRequest;
import app.appointment.service.notification.application.NotificationOneUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.concurrent.Flow;

@Controller
@Slf4j
@AllArgsConstructor
public class NotificationController {
    private final NotificationOneUser notificationOneUser;
    @MessageMapping("/websocket-endpoint")
    @SendTo("/topic/notifications")
    public String sendToUser( LoginRequest message) {
        // Aquí puedes hacer lógica adicional si es necesario
        //notificationOneUser.execute(destination, message);
        log.info(message.toString());
        return message.toString();
    }
    @SubscribeMapping("/websocket-endpoint")
    public String initialReply() throws Exception {
        return "Welcome to the chat room.";
    }
}
