package app.appointment.service.patient.infrastructure.adapter.driver;

import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoPatientRepository extends MongoRepository<PatientEntity, String> {
    Optional<PatientEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String email);

    Optional<PatientEntity> findByUsername(String username);
}
