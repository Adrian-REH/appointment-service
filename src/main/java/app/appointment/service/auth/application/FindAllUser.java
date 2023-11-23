package app.appointment.service.auth.application;

import app.appointment.service.auth.infrastructure.adapter.driver.UserRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindAllUser {
    private final UserRepository userRepository;




    public Iterable<User> execute(){
        return userRepository.findAll();
    }
}
