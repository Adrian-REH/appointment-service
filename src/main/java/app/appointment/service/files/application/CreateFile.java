package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileRequest;
import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import app.appointment.service.laboratory.domain.port.LaboratoryRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.odontogram.domain.port.OdontogramaRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateFile {
    private final FileRepository fileRepository;
    private final MedicalRepository medicalRepository;
    private final PatientRepository patientRepository;
    private final OdontogramaRepository odontogramaRepository;
    public FileResponse execute(FileRequest fileRequest) {
        if (fileRequest.getIdOdontograma().isBlank() && fileRequest.getForm().isBlank() && fileRequest.getStudies().isBlank() && fileRequest.getPrescriptions().isBlank() && fileRequest.getIdLaboratory().isBlank())
        {
            throw new ServiceException("No contiene archivos");
        }
        if (medicalRepository.findByUsername(fileRequest.getUsernameMedical()).isEmpty()){
            throw new ServiceException(600, "No existe el medico");
        }
        if (patientRepository.findByUsername(fileRequest.getUsernamePatient()).isEmpty()){
            throw new ServiceException(600, "No existe el paciente");
        }

        odontogramaRepository.findById(fileRequest.getIdOdontograma());


        //laboratoryRepository.findById(fileRequest.getIdMedical());
        return fileRepository.save(fileRequest);
    }
}
