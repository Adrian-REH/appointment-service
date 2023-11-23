package app.appointment.service.auth.domain.port;




import app.appointment.service.auth.domain.model.RegisterRequest;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findOne(String username);
    User findById(String id);
}
