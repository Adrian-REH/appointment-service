package app.appointment.service.patient.application;

import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePatientById {

    private final PatientRepository patientRepository;
    public void execute(String id) {
        patientRepository.deleteById(id);
    }
}
