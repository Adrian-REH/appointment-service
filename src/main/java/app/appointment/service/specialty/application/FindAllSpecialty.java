package app.appointment.service.specialty.application;

import app.appointment.service.specialty.domain.model.SpecialtyResponse;
import app.appointment.service.specialty.domain.port.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FindAllSpecialty {
    private final SpecialtyRepository specialtyRepository;

    public List<SpecialtyResponse> execute() {
        return specialtyRepository.getAllSpecialtys();
    }
}
