package app.appointment.service.specialty.application;

import app.appointment.service.specialty.domain.port.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteSpecialtyById {
    private final SpecialtyRepository specialtyRepository;
    public void execute(String id) {
        specialtyRepository.delete(id);
    }
}
