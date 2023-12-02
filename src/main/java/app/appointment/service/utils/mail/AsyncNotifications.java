package app.appointment.service.utils.mail;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AsyncNotifications {

    private final AsyncMailService asyncMailService ;

    @Async
    public void sendEmailAndUserDatabase(String emailContent){

       this.asyncMailService.sendEmail("adrianherrera.r.e@gmail.com","Intentaste iniciar session!",emailContent);

    }


}
