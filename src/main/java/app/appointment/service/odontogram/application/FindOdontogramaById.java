package app.appointment.service.odontogram.application;

import app.appointment.service.odontogram.domain.model.OdontogramaResponse;
import app.appointment.service.odontogram.domain.port.OdontogramaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindOdontogramaById {
    private final OdontogramaRepository odontogramaRepository;
    public OdontogramaResponse execute(String id) {
        return odontogramaRepository.findById(id);
    }
}
