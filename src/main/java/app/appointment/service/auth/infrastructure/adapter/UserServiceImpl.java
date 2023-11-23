package app.appointment.service.auth.infrastructure.adapter;

import app.appointment.service.auth.domain.exception.EmailAlreadyExistsException;
import app.appointment.service.auth.domain.exception.RegisterRequestParamNullException;
import app.appointment.service.auth.domain.exception.UsernameAlreadyExistsException;
import app.appointment.service.auth.domain.model.RegisterRequest;
import app.appointment.service.auth.domain.port.RoleService;
import app.appointment.service.auth.domain.port.UserService;
import app.appointment.service.auth.infrastructure.adapter.driver.UserRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.MongoMedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.infrastructure.adapter.driver.MongoPatientRepository;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userService")
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private RoleService roleService;
    private MongoMedicalRepository medicalRepository;
    private MongoPatientRepository patientRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userRepository.findByUsername(username);
        Optional<MedicalEntity> medical =medicalRepository.findByUsername(username);
        Optional<PatientEntity> patient =patientRepository.findByUsername(username);
        String password ="";
        String role ="";

        if (medical.isPresent()){
            password= medical.get().getPassword();
            role= medical.get().getRole().getId();
        }else if (patient.isPresent()){
            password= patient.get().getPassword();
            role= patient.get().getRole().getId();

        }else {
            throw new ServiceException(600,"No se encontro el usuario");
        }

        return new org.springframework.security.core.userdetails.User(username, password, getAuthority(role));
    }

    private Set<SimpleGrantedAuthority> getAuthority(String role) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(String id) {
        if(id == null){ // programación defensiva
            throw new IllegalArgumentException("Valor id de usuario incorrecto, no es posible realizar la búsqueda.");
        }
        throw new NoSuchElementException("No se ha encontrado el usuario solicitado.");
    }



}
