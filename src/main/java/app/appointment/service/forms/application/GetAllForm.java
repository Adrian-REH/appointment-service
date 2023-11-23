package app.appointment.service.forms.application;

import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.domain.port.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllForm {
    private final FormRepository formRepository;
    public List<FormResponse> execute() {
        return formRepository.findAll();
    }
}
