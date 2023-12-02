package app.appointment.service.utils.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class AsyncPushNotificationService {



    @Async
    public void sendPushNotification(String token, String title, String body) {

        try {
            Message message = Message.builder()
                    .setToken(token)
                    .putData("title", title)
                    .putData("message", body)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            log.info(response);

        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage(), e);
        }

    }






}
