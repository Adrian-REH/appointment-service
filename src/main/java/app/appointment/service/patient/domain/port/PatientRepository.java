package app.appointment.service.patient.domain.port;

import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;

import java.util.List;

public interface PatientRepository {
    PatientResponse save(PatientRequest patientRequest);

    List<PatientResponse> findAll();

    PatientResponse findByEmail(String email);

    PatientResponse findById(String id);

    PatientResponse updateById(String id, PatientRequest patientRequest);

    void deleteById(String id);
}
