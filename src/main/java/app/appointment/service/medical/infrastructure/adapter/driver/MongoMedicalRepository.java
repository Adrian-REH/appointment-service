package app.appointment.service.medical.infrastructure.adapter.driver;

import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoMedicalRepository extends MongoRepository<MedicalEntity, String> {
    Optional<MedicalEntity> findByEmail(String email);
    Optional<MedicalEntity> findByUsername(String email);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String email);

}
