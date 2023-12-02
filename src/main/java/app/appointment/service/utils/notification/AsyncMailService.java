package app.appointment.service.utils.notification;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class AsyncMailService {

    private final JavaMailSender mailSender;


    public static String licenseTrialPeriodActiveEmail() {


        return "msg";
    }

    @Async
    public void sendEmail(String to, String subject, String text) {


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("coli969696@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            //helper.setText(licenseTrialPeriodActiveEmail(), true); // true indica que el texto es HTML

/*
            File pdfFile = new File("C:/Users/Usuario/Desktop/Carta_Adrian.pdf");
            helper.addAttachment(pdfFile.getName(), pdfFile);
*/

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);

    }






}
