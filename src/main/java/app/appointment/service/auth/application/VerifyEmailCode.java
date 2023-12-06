package app.appointment.service.auth.application;

import app.appointment.service.auth.infrastructure.adapter.driver.UserRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import app.appointment.service.utils.AppUtil;
import app.appointment.service.utils.dto.EmailNotificationDto;
import app.appointment.service.utils.notification.AsyncMailService;
import app.appointment.service.utils.notification.AsyncNotifications;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerifyEmailCode {
    private final PatientRepository patientRepository;
    private final MedicalRepository medicalRepository;
    private final AsyncNotifications asyncNotifications;
    private final LocalDateTime now = LocalDateTime.now();
    private BCryptPasswordEncoder bcryptEncoder;

    public void execute(String email){
        Optional<PatientEntity> patient = patientRepository.findByEmail(email);
        Optional<MedicalEntity> medical = medicalRepository.findByEmail(email);

        var twoFactorCode = AppUtil.generatedCode();
        var notifyDto = EmailNotificationDto.builder()
                .patient("")
                .medical("")
                .twoFactorCode(twoFactorCode)
                .twoFactorCreatedCode(now)
                .twoFactorExpiryCode(now.plusMinutes(30))
                .email(email).build();


        patient.ifPresent(patientEntity -> {
            notifyDto.setPatient(patientEntity.getName() );
            patientEntity.setTwoFactorCode(BCrypt.hashpw(twoFactorCode,BCrypt.gensalt(10)));
            patientEntity.setTwoFactorCreatedCode(notifyDto.getTwoFactorCreatedCode());
            patientEntity.setTwoFactorExpiryCode(notifyDto.getTwoFactorExpiryCode());
            patientEntity.setTwoFactorCodeUsed(false);
            patientRepository.updateTwoFactor(patientEntity);
        });
        medical.ifPresent(medicalEntity -> {
            notifyDto.setMedical(medicalEntity.getName());
            medicalEntity.setTwoFactorCode(BCrypt.hashpw(twoFactorCode,BCrypt.gensalt(10)));
            medicalEntity.setTwoFactorCreatedCode(now);
            medicalEntity.setTwoFactorExpiryCode(now.plusMinutes(30));
            medicalEntity.setTwoFactorCodeUsed(false);
            medicalRepository.updateTwoFactor(medicalEntity);

        });

        if(patient.isPresent() || medical.isPresent()){

            asyncNotifications.emailNotifyVerifyEmailCode(notifyDto);
        }

    }
}
