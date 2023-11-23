package app.appointment.service.utils.mail;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan Giovanni Hernandez
 * @company Finansoportes Consulting
 * @created 08/07/2022
 */
@Component
@AllArgsConstructor
public class AsyncNotifications {

    private final AsyncMailService asyncMailService ;

    @Async
    public void sendEmailAndUserDatabase(String emailContent){

       this.asyncMailService.sendEmail("adrianherrera.r.e@gmail.com","Intentaste iniciar session!",emailContent);

    }


}
