package app.appointment.service.auth.infrastructure.web;

import app.appointment.service.auth.application.FindAllUser;
import app.appointment.service.auth.infrastructure.adapter.AuthService;
import app.appointment.service.auth.domain.model.JwtResponse;
import app.appointment.service.auth.domain.model.LoginRequest;
import app.appointment.service.utils.mail.AsyncMailService;
import app.appointment.service.utils.mail.AsyncNotifications;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }



}












