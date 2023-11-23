package app.appointment.service.specialty.application;

import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.specialty.domain.model.SpecialtyRequest;
import app.appointment.service.specialty.domain.model.SpecialtyResponse;
import app.appointment.service.specialty.domain.port.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateSpecialtyById {
    private final SpecialtyRepository specialtyRepository;
    private final MedicalRepository medicalRepository;
    public SpecialtyResponse execute(String id, SpecialtyRequest request) {
        medicalRepository.findById(request.getIdMedical());
        return specialtyRepository.update(id, request);
    }
}
