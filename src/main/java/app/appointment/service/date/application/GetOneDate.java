package app.appointment.service.date.application;

import app.appointment.service.date.domain.model.DateResponse;
import app.appointment.service.date.domain.port.DateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOneDate {
    private final DateRepository dateRepository;
    public DateResponse execute(String id) {
        return dateRepository.findById(id);
    }
}
