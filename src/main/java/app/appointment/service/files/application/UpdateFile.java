package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileRequest;
import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateFile {
    private final FileRepository fileRepository;
    private final MedicalRepository medicalRepository;
    private final PatientRepository patientRepository;
    public FileResponse execute(String id, FileRequest request) {
        if (medicalRepository.findByUsername(request.getUsernameMedical()).isEmpty()){
            throw new ServiceException(600, "No existe el medico");
        }
        if (patientRepository.findByUsername(request.getUsernamePatient()).isEmpty()){
            throw new ServiceException(600, "No existe el paciente");
        }
        return fileRepository.updateById(id, request);
    }
}
