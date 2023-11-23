package app.appointment.service.date.infrastructure.adapter.driver;

import app.appointment.service.date.infrastructure.adapter.driver.entity.DateEntity;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDateRepository extends MongoRepository<DateEntity, String>{

}
