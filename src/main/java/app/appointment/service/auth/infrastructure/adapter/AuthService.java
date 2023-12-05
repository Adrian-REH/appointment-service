package app.appointment.service.auth.infrastructure.adapter;

import app.appointment.service.auth.domain.model.JwtResponse;
import app.appointment.service.auth.domain.model.JwtUserClaims;
import app.appointment.service.auth.domain.model.LoginRequest;
import app.appointment.service.auth.domain.model.RegisterRequest;
import app.appointment.service.auth.domain.port.UserService;
import app.appointment.service.auth.infrastructure.adapter.driver.UserRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;
import app.appointment.service.auth.infrastructure.web.security.TokenProvider;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import com.google.api.Http;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que lleva a cabo la autenticación utilizando JWT
 * <p>
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 * <p>
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final PatientRepository patientRepository;
    private final MedicalRepository medicalRepository;
    private final TokenProvider jwtTokenUtil;


    public JwtResponse login(LoginRequest loginRequest) {
        var optMedical = medicalRepository.findByUsername(loginRequest.getUsername());
        var optPatient = patientRepository.findByUsername(loginRequest.getUsername());
        JwtUserClaims jwtUserClaims = JwtUserClaims.builder().isMedical(loginRequest.getIsMedical()).username(loginRequest.getUsername()).build();
        if (loginRequest.getIsMedical() && optMedical.isEmpty()) {
            throw new ServiceException(600,HttpStatus.UNAUTHORIZED, "");
        }
        if (!loginRequest.getIsMedical() && optPatient.isEmpty()) {
            throw new ServiceException(601,HttpStatus.UNAUTHORIZED, "");
        }

        final Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = jwtTokenUtil.generateToken(authentication, jwtUserClaims);

        return new JwtResponse(token);
    }


}
