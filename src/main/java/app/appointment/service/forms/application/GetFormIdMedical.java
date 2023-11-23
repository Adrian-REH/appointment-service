package app.appointment.service.forms.application;

import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.domain.port.FormRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetFormIdMedical {
    private final FormRepository formRepository;
    private final MedicalRepository medicalRepository;
    public List<FormResponse> execute(String id) {
        medicalRepository.findById(id);
        return formRepository.findByIdMedical(id);
    }
}
