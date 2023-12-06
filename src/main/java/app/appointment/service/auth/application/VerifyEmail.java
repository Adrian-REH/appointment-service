package app.appointment.service.auth.application;

import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import app.appointment.service.utils.AppUtil;
import app.appointment.service.utils.dto.EmailNotificationDto;
import app.appointment.service.utils.exception.ServiceException;
import app.appointment.service.utils.notification.AsyncNotifications;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerifyEmail {
    private final PatientRepository patientRepository;
    private final MedicalRepository medicalRepository;
    private final AsyncNotifications asyncNotifications;
    private final LocalDateTime now = LocalDateTime.now();
    private BCryptPasswordEncoder bcryptEncoder;
    private ModelMapper modelMapper;

    public void execute(String username){
        Optional<PatientEntity> patient = patientRepository.findByUsername(username);
        Optional<MedicalEntity> medical = medicalRepository.findByUsername(username);

        if(patient.isEmpty() && medical.isEmpty()){
            throw new ServiceException(600,"No existe el usuario");
        }
        medical.ifPresent(medicalEntity -> {
            medicalEntity.setEmailVerified(true);
            medicalRepository.update(medicalEntity);
        });
        patient.ifPresent(patientEntity -> {
            patientEntity.setEmailVerified(true);
            patientRepository.update(patientEntity);
        });


    }
}
