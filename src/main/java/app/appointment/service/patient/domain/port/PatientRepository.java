package app.appointment.service.patient.domain.port;

import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    PatientResponse save(PatientRequest patientRequest);

    List<PatientResponse> findAll();

    PatientResponse findByEmail(String email);

    PatientResponse findById(String id);
    Optional<PatientEntity> findByUsername(String username);

    PatientResponse updateById(String id, PatientRequest patientRequest);

    void deleteById(String id);
}
