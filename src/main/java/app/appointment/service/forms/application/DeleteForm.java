package app.appointment.service.forms.application;

import app.appointment.service.forms.domain.port.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteForm {
    private final FormRepository formRepository;
    public void execute(String id) {
        formRepository.deleteById(id);
    }
}
