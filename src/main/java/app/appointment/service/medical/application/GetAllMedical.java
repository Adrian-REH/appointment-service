package app.appointment.service.medical.application;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.MedicalDatasource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllMedical {

    private final MedicalRepository medicalRepository;
    public List<MedicalResponse> execute() {

        return medicalRepository.getAll();
    }
}
