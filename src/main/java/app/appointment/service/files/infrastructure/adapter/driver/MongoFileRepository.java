package app.appointment.service.files.infrastructure.adapter.driver;

import app.appointment.service.files.infrastructure.adapter.driver.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MongoFileRepository extends MongoRepository<FileEntity, String> {
    List<FileEntity> findAllByUsernamePatient(String patient);
    List<FileEntity> findAllByUsernameMedical(String patient);

    Optional<FileEntity> findByUsernameMedical(String id);
    Optional<FileEntity> findByUsernamePatient(String id);
    @Query("{ 'usernameMedical' : ?0, 'usernamePatient' : ?1 }")
    Optional<FileEntity> findByUsernamePatientAndUsernameMedical(String usernamePatient, String usernameMedical);

}
