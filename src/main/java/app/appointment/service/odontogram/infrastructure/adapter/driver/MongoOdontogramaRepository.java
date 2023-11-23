package app.appointment.service.odontogram.infrastructure.adapter.driver;

import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.odontogram.infrastructure.adapter.driver.entity.OdontogramaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOdontogramaRepository extends MongoRepository<OdontogramaEntity, String> {
}
