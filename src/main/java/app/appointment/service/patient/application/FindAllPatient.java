package app.appointment.service.patient.application;

import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllPatient {

    private final PatientRepository patientRepository;
    public List<PatientResponse> execute() {
        return patientRepository.findAll();
    }
}
