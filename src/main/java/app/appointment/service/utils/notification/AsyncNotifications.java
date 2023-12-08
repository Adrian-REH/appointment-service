package app.appointment.service.utils.notification;


import app.appointment.service.utils.AppUtil;
import app.appointment.service.utils.dto.EmailNotificationDto;
import app.appointment.service.utils.dto.PushNotificationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AsyncNotifications {

    private final AsyncMailService asyncMailService ;
    private final AsyncPushNotificationService notificationService ;
    @Async
    public void emailNotifyNewAccount(EmailNotificationDto notificationDto){


        this.asyncMailService.sendEmail(notificationDto.getEmail(),"Se creo una cuenta en Appointment", AppUtil.generateHTMLNewAccount(notificationDto));

    }

    @Async
    public void emailNotifySessionSuccess(EmailNotificationDto notificationDto){

        this.asyncMailService.sendEmail(notificationDto.getEmail(),"Inicio de session exitoso", AppUtil.generateHTMLInicioSession(notificationDto));

    }
    @Async
    public void emailNotifyVerifyEmailCode(EmailNotificationDto notificationDto){

        this.asyncMailService.sendEmail(notificationDto.getEmail(),"Codigo de verificacion",AppUtil.generateHTMLVerifyEmailCode(notificationDto));

    }
    @Async
    public void emailNotifyVerifyEmail(EmailNotificationDto notificationDto){

        this.asyncMailService.sendEmail(notificationDto.getEmail(),"Restablece tu contraseña de Appointment",AppUtil.generateHTMLVerifyEmail(notificationDto));

    }
    @Async
    public void emailNotifyResetPassword(EmailNotificationDto notificationDto){

        this.asyncMailService.sendEmail(notificationDto.getEmail(),"Cambio de clave",AppUtil.generateHTMLResetPassword(notificationDto));

    }
    @Async
    public void emailNotifyNewAppointment(EmailNotificationDto notificationDto){

        this.asyncMailService.sendEmail(notificationDto.getEmail(),"Tienes un nuevo turno",AppUtil.generateHTMLNewAppointment(notificationDto));

    }
    @Async
    public void pushNotifyChangeAppointment(PushNotificationDto notificationDto){
        var bodyText = notificationDto.getPatient();
        bodyText += (notificationDto.getIsDeleteAppointment() ? " Cancelo el turno del ": " Cambio el turno para el " ) + notificationDto.getDateAppointment();
        var title = notificationDto.getIsDeleteAppointment()?"Cancelaron el turno":"Cambiaron tu turno";

        notificationService.sendPushNotification(notificationDto.getToken(), title,bodyText);
    }
    @Async
    public void pushNotifyNewAppointment(PushNotificationDto notificationDto){

        var bodyText = notificationDto.getPatient() + notificationDto.getMedical() + " Creo el turno para el " + notificationDto.getDateAppointment();
        var title = "Crearon un turno";

        notificationService.sendPushNotification(notificationDto.getToken(), title,bodyText);

    }
    @Async
    public void pushNotifyNewFav(PushNotificationDto notificationDto){

        var bodyText = notificationDto.getPatient()+ " Te agrego a favoritos";
        var title = "Te agregaron a favoritos";

        notificationService.sendPushNotification(notificationDto.getToken(), title,bodyText);

    }
}
