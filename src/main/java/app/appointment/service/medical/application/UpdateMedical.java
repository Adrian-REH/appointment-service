package app.appointment.service.medical.application;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateMedical {

    private final MedicalRepository medicalRepository;
    public MedicalResponse execute(String id, MedicalRequest medicalEntity) {
        MedicalResponse response = new MedicalResponse();
        try {
            response = medicalRepository.update(id,medicalEntity);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }
}
