package app.appointment.service.auth.infrastructure.adapter;

import app.appointment.service.auth.domain.model.JwtResponse;
import app.appointment.service.auth.domain.model.LoginRequest;
import app.appointment.service.auth.domain.model.RegisterRequest;
import app.appointment.service.auth.domain.port.UserService;
import app.appointment.service.auth.infrastructure.adapter.driver.UserRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;
import app.appointment.service.auth.infrastructure.web.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que lleva a cabo la autenticación utilizando JWT
 *
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 *
 *
 */
@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenProvider jwtTokenUtil;



    public JwtResponse login(LoginRequest loginRequest){

        final Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = jwtTokenUtil.generateToken(authentication);

        return new JwtResponse(token);
    }




}
