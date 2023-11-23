package app.appointment.service.patient.application;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePatient {

    private final PatientRepository patientRepository;
    public PatientResponse execute(PatientRequest patientRequest) {
        return patientRepository.save(patientRequest);
    }
}
