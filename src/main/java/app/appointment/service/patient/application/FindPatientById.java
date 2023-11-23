package app.appointment.service.patient.application;

import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPatientById {

    private final PatientRepository patientRepository;
    public PatientResponse execute(String id) {
        return patientRepository.findById(id);
    }
}
