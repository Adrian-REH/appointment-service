package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileRequest;
import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateFile {
    private final FileRepository fileRepository;
    private final MedicalRepository medicalRepository;
    private final PatientRepository patientRepository;
    public FileResponse execute(String id, FileRequest request) {
        patientRepository.findById(request.getIdPatient());
        medicalRepository.findById(request.getIdMedical());

        return fileRepository.updateById(id, request);
    }
}
