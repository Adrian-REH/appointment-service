package app.appointment.service.specialty.application;

import app.appointment.service.specialty.domain.model.SpecialtyResponse;
import app.appointment.service.specialty.domain.port.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindSpecialtyById {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyResponse execute(String id) {
        return specialtyRepository.getById(id);
    }
}
