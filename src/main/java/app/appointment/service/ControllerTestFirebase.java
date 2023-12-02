package app.appointment.service;


import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/firebase")
@RestController
@AllArgsConstructor
public class ControllerTestFirebase {

    @GetMapping
    public ResponseEntity<String> sendNotification() {
        try {
            Message message = Message.builder()
                    .setToken("c1ptAR0HSUW7nq2fyO2TWf:APA91bFWweREnjq-rvfY5jiQLfGyWXTLvFt5-CUB-rm8ZFaD_HuraI794HQko4mozln_Z_ML-qquhDG4EWLwjxUEIkLU3uMTa-8SxmdBXsno83d1HcoBXObq-3L9TVd-774VBKJzUOIn")
                    .putData("title", "experimento")
                    .putData("body", "experimento 626")
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);

            return ResponseEntity.ok("Notification sent successfully. Response: " + response);
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending notification: " + e.getMessage());
        }
    }
}
