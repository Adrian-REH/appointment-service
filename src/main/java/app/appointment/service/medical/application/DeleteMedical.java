package app.appointment.service.medical.application;

import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.MedicalDatasource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DeleteMedical {
    private final MedicalRepository medicalRepository;

    public MedicalResponse execute(String id) {
        MedicalResponse result = null;
        try {
            medicalRepository.delete(id);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return  result;
    }
}
