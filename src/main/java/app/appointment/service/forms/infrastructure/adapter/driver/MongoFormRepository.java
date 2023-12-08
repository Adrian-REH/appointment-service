package app.appointment.service.forms.infrastructure.adapter.driver;

import app.appointment.service.forms.infrastructure.adapter.driver.entity.FormEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MongoFormRepository  extends MongoRepository<FormEntity, String> {
    List<FormEntity> findAllByUsernamePatient(String patient);
    List<FormEntity> findAllByUsernameMedical(String medical);

    Optional<FormEntity> findByUsernameMedical(String username);
    Optional<FormEntity> findByUsernamePatient(String username);
    @Query("{ 'usernameMedical' : ?0, 'usernamePatient' : ?1 }")
    Optional<FormEntity> findByUsernamePatientAndUsernameMedical(String usernamePatient, String usernameMedical);

}
