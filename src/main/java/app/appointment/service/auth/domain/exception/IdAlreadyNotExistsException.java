package app.appointment.service.auth.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdAlreadyNotExistsException extends ResponseStatusException {

    public IdAlreadyNotExistsException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
