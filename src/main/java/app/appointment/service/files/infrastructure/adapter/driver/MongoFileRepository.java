package app.appointment.service.files.infrastructure.adapter.driver;

import app.appointment.service.favs.infrastructure.adapter.driver.entity.FavEntity;
import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.infrastructure.adapter.driver.entity.FileEntity;
import app.appointment.service.forms.infrastructure.adapter.driver.entity.FormEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MongoFileRepository extends MongoRepository<FileEntity, String> {
    List<FileEntity> findAllByIdPatient(String patient);
    List<FileEntity> findAllByIdMedical(String patient);

    Optional<FileEntity> findByIdMedical(String id);
    Optional<FileEntity> findByIdPatient(String id);
    @Query("{ 'idMedical' : ?0, 'idPatient' : ?1 }")
    Optional<FileEntity> findByIdPatientAndIdMedical(String idPatient, String idMedical);

}
