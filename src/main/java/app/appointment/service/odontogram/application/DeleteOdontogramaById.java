package app.appointment.service.odontogram.application;

import app.appointment.service.odontogram.domain.port.OdontogramaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteOdontogramaById {
    private final OdontogramaRepository odontogramaRepository;

    public void execute(String id) {
        odontogramaRepository.deleteById(id);
    }
}
