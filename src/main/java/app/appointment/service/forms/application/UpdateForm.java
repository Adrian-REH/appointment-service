package app.appointment.service.forms.application;

import app.appointment.service.forms.domain.model.FormRequest;
import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.domain.port.FormRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateForm {
    private final FormRepository formRepository;
    private final MedicalRepository medicalRepository;
    private final PatientRepository patientRepository;
    public FormResponse execute(String id, FormRequest request) {
        if (medicalRepository.findByUsername(request.getUsernameMedical()).isEmpty()){
            throw new ServiceException(600, "No existe el medico");
        }
        if (patientRepository.findByUsername(request.getUsernamePatient()).isEmpty()){
            throw new ServiceException(600, "No existe el paciente");
        }

        return formRepository.update(id, request);
    }
}
