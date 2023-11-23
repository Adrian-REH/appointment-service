package app.appointment.service.odontogram.application;

import app.appointment.service.odontogram.domain.model.OdontogramaResponse;
import app.appointment.service.odontogram.domain.port.OdontogramaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FindAllOdontograma {
    private final OdontogramaRepository odontogramaRepository;

    public List<OdontogramaResponse> execute() {
        return odontogramaRepository.findAll();
    }
}
