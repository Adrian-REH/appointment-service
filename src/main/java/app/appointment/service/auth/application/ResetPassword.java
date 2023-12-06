package app.appointment.service.auth.application;

import app.appointment.service.auth.domain.model.ResetPasswordRequest;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import app.appointment.service.utils.AppUtil;
import app.appointment.service.utils.dto.EmailNotificationDto;
import app.appointment.service.utils.exception.ServiceException;
import app.appointment.service.utils.notification.AsyncNotifications;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResetPassword {
    private final PatientRepository patientRepository;
    private final MedicalRepository medicalRepository;
    private final AsyncNotifications asyncNotifications;
    private final LocalDateTime now = LocalDateTime.now();
    private BCryptPasswordEncoder bcryptEncoder;

    public void execute(ResetPasswordRequest request){
        Optional<PatientEntity> patient = patientRepository.findByEmail(request.getEmail());
        Optional<MedicalEntity> medical = medicalRepository.findByEmail(request.getEmail());

        if(patient.isEmpty() && medical.isEmpty()){
            throw new ServiceException(600,"El usuario no existe");
        }
        var notifyDto = EmailNotificationDto.builder()
                .patient("")
                .medical("")
                .email(request.getEmail())
                .build();

        medical.ifPresent(medicalEntity -> {
            notifyDto.setMedical(medicalEntity.getName());
            notifyDto.setUsername(medicalEntity.getUsername());
            medicalEntity.setPassword(request.getPasswordHash());
            medicalRepository.updateTwoFactor(medicalEntity);
        });
        patient.ifPresent(patientEntity -> {
            notifyDto.setMedical(patientEntity.getName());
            notifyDto.setUsername(patientEntity.getUsername());
            patientEntity.setPassword(request.getPasswordHash());
            patientRepository.updateTwoFactor(patientEntity);
        });

        asyncNotifications.emailNotifyResetPassword(notifyDto);
    }
}
