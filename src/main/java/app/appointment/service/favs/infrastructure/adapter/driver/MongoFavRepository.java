package app.appointment.service.favs.infrastructure.adapter.driver;

import app.appointment.service.favs.infrastructure.adapter.driver.entity.FavEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MongoFavRepository extends MongoRepository<FavEntity, String> {
    @Query("{ 'idMedical' : ?0, 'idPatient' : ?1 }")
    Optional<FavEntity> findByIdMedicalAndIdPatient(String idMedical, String idPatient);

    Optional<FavEntity> findByIdMedical(String id);
    Optional<FavEntity> findByIdPatient(String id);
}
