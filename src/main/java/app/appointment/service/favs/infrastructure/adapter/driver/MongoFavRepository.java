package app.appointment.service.favs.infrastructure.adapter.driver;

import app.appointment.service.favs.infrastructure.adapter.driver.entity.FavEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MongoFavRepository extends MongoRepository<FavEntity, String> {
    @Query("{ 'usernameMedical' : ?0, 'usernamePatient' : ?1 }")
    Optional<FavEntity> findByUsernameMedicalAndUsernamePatient(String usernameMedical, String usernamePatient);

}
