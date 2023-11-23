package app.appointment.service.date.application;

import app.appointment.service.date.domain.model.DateRequest;
import app.appointment.service.date.domain.model.DateResponse;
import app.appointment.service.date.domain.port.DateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllDate {
    private final DateRepository dateRepository;
    public List<DateResponse> execute() {
        return dateRepository.findAll();
    }

}
