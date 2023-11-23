package app.appointment.service.specialty.infrastructure.adapter.driver;

import app.appointment.service.specialty.infrastructure.adapter.driver.entity.SpecialtyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoSpecialtyRepository extends MongoRepository<SpecialtyEntity,String> {

    Optional<SpecialtyEntity> findByIdMedical(String idMedical);
}
