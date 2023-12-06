package app.appointment.service.auth.infrastructure.web;

import app.appointment.service.auth.application.ResetPassword;
import app.appointment.service.auth.application.VerifyCode;
import app.appointment.service.auth.application.VerifyEmail;
import app.appointment.service.auth.application.VerifyEmailCode;
import app.appointment.service.auth.domain.model.*;
import app.appointment.service.auth.infrastructure.adapter.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final VerifyEmailCode verifyEmailCode;
    private final VerifyCode verifyCode;
    private final ResetPassword resetPassword;
    private final VerifyEmail verifyEmail;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        log.info("Login request" + loginRequest.toString());

        var response = authService.login(loginRequest);
        log.info("Login Response" + response.toString());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/verify-email/{username}")
    public ResponseEntity<String> verifyEmail(@PathVariable @Valid @NotBlank  String username){
        verifyEmail.execute(username);
        String htmlBody = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style>body{font-family:Arial,sans-serif;background-color:#f4f4f4;text-align:center;margin:0;padding:20px}h1{color:#007bff}p{color:#555;font-size:18px}</style></head><body><h1>Email verificado</h1><p> Enhorabuena, Tu correo electronico ha sido verificado correctamente.</p></body></html>";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(htmlBody, headers, HttpStatus.OK);

    }
    @PatchMapping("/send-email-code")
    public ResponseEntity<Void> sendEmailCode(@RequestBody @Valid  SendEmailCodeRequest request){
        verifyEmailCode.execute(request.getEmail());
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/verify-code")
    public ResponseEntity<Void> verifyCode(@RequestBody @Valid VerifyCodeRequest verifyCodeRequest){
        verifyCode.execute(verifyCodeRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRequest request){
        resetPassword.execute(request);
        return ResponseEntity.ok().build();
    }
}












