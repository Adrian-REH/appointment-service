package app.appointment.service.forms.application;

import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.domain.port.FormRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetFormIdPatientAndIdMedical {
    private final FormRepository formRepository;
    private final MedicalRepository medicalRepository;
    private final PatientRepository patientRepository;
    public FormResponse execute(String idPatient, String idMedical) {
        medicalRepository.findById(idMedical);
        patientRepository.findById(idPatient);
        return formRepository.findByIdPatientAndIdMedical(idPatient, idMedical);
    }
}
