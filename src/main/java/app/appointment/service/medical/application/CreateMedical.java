package app.appointment.service.medical.application;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.MedicalDatasource;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMedical {

    private final MedicalRepository medicalRepository;
    public MedicalResponse execute(MedicalRequest medicalEntity) {



        return medicalRepository.save(medicalEntity);
    }
}
