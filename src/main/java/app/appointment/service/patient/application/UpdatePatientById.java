package app.appointment.service.patient.application;

import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class UpdatePatientById {

    private final PatientRepository patientRepository;
    public PatientResponse execute(String id, PatientRequest patientRequest) {


        return patientRepository.updateById(id,patientRequest);
    }
}
