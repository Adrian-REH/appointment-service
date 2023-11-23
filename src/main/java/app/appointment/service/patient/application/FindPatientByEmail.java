package app.appointment.service.patient.application;

import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindPatientByEmail {

    private final PatientRepository patientRepository;
    public PatientResponse execute(String email) {
        return patientRepository.findByEmail(email);
    }
}
