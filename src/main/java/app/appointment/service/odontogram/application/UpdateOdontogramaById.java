package app.appointment.service.odontogram.application;

import app.appointment.service.odontogram.domain.model.OdontogramaRequest;
import app.appointment.service.odontogram.domain.model.OdontogramaResponse;
import app.appointment.service.odontogram.domain.port.OdontogramaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateOdontogramaById {
    private final OdontogramaRepository odontogramaRepository;

    public OdontogramaResponse execute(String id, OdontogramaRequest odontogramaRequest) {
        odontogramaRepository.findById(id);
        return odontogramaRepository.update(odontogramaRequest);
    }
}
