package app.appointment.service.forms.application;

import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.domain.port.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOneForm {
    private final FormRepository formRepository;
    public FormResponse execute(String id) {
        return formRepository.findById(id);
    }
}
