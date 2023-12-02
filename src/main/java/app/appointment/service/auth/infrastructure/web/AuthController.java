package app.appointment.service.auth.infrastructure.web;

import app.appointment.service.auth.infrastructure.adapter.AuthService;
import app.appointment.service.auth.domain.model.JwtResponse;
import app.appointment.service.auth.domain.model.LoginRequest;
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


    @PostMapping("/reset-password")
    public ResponseEntity<String> generateCode(@RequestBody @Valid String email){
        //Todo hacer la logica para que genere un codigo de verificacion y lo envie al email, y lo verifique
        return ResponseEntity.ok("");
    }
    @PostMapping("/verify-code")
    public ResponseEntity<Boolean> verifyCode(@RequestBody @Valid Integer code){
        //Todo hacer la logica para verificar el codigo
        return ResponseEntity.ok(true);
    }

    //TODO Resetea la contraseña usando un Patch en Medical o Patient
    @PatchMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody @Valid LoginRequest loginRequest){
        //Todo hacer la logica para cambiar la contraseña
        return ResponseEntity.ok(true);
    }
}












