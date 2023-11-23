package app.appointment.service.auth.infrastructure.adapter.driver;

import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleById(String id);
}