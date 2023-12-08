package app.appointment.service.forms.application;

import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.domain.port.FormRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetFormIdPatient {
    private final FormRepository formRepository;
    private final PatientRepository patientRepository;
    public List<FormResponse> execute(String id) {
        if(patientRepository.findByUsername(id).isEmpty()){
            throw new ServiceException(600, "No existe el paciente");
        }
        return formRepository.findByUsernamePatient(id);
    }
}
