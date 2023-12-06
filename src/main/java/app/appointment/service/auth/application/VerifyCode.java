package app.appointment.service.auth.application;

import app.appointment.service.auth.domain.model.VerifyCodeRequest;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import app.appointment.service.utils.AppUtil;
import app.appointment.service.utils.dto.EmailNotificationDto;
import app.appointment.service.utils.exception.ServiceException;
import app.appointment.service.utils.notification.AsyncNotifications;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerifyCode {
    private final PatientRepository patientRepository;
    private final MedicalRepository medicalRepository;
    private final AsyncNotifications asyncNotifications;
    private final LocalDateTime now = LocalDateTime.now();
    private BCryptPasswordEncoder bcryptEncoder;

    public Boolean execute(VerifyCodeRequest verifyCodeRequest){
        Optional<PatientEntity> patient = patientRepository.findByEmail(verifyCodeRequest.getEmail());
        Optional<MedicalEntity> medical = medicalRepository.findByEmail(verifyCodeRequest.getEmail());

        if(patient.isEmpty() && medical.isEmpty()) {
            throw new ServiceException(600,"El usuario no existe");
        }
        patient.ifPresent(patientEntity -> {

            verified(verifyCodeRequest.getTwoFactorCode(),patientEntity.getTwoFactorCode(),patientEntity.getTwoFactorExpiryCode(),patientEntity.getTwoFactorCodeUsed());
            patientEntity.setTwoFactorCodeUsed(true);
            patientRepository.updateTwoFactor(patientEntity);
        });
        medical.ifPresent(medicalEntity -> {
            verified(verifyCodeRequest.getTwoFactorCode(),medicalEntity.getTwoFactorCode(),medicalEntity.getTwoFactorExpiryCode(),medicalEntity.getTwoFactorCodeUsed());
            medicalEntity.setTwoFactorCodeUsed(true);
            medicalRepository.updateTwoFactor(medicalEntity);

        });


        return true;

    }

    private void verified(String twoFactorCode, String twoFactorCodeHash, LocalDateTime twoFactorExpiryCode, Boolean twoFactorCodeUsed) {
        if(!BCrypt.checkpw(twoFactorCode,twoFactorCodeHash)){
            throw new ServiceException(600,"El codigo no es valido");
        }
        if(now.isAfter(twoFactorExpiryCode)) {
            throw new ServiceException(600,"Paso el tiempo de validacion");
        }
        if(twoFactorCodeUsed){
            throw new ServiceException(600,"El codigo fue usado");
        }
    }


}
