package app.appointment.service.forms.infrastructure.adapter.driver;

import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.infrastructure.adapter.driver.entity.FormEntity;
import app.appointment.service.forms.infrastructure.adapter.driver.entity.FormEntity;
import app.appointment.service.odontogram.infrastructure.adapter.driver.entity.OdontogramaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MongoFormRepository  extends MongoRepository<FormEntity, String> {
    List<FormEntity> findAllByIdPatient(String patient);
    List<FormEntity> findAllByIdMedical(String medical);

    Optional<FormEntity> findByIdMedical(String id);
    Optional<FormEntity> findByIdPatient(String id);
    @Query("{ 'idMedical' : ?0, 'idPatient' : ?1 }")
    Optional<FormEntity> findByIdPatientAndIdMedical(String idPatient, String idMedical);

}
