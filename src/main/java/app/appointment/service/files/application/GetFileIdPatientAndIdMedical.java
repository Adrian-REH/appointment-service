package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetFileIdPatientAndIdMedical {
    private final FileRepository fileRepository;


    public FileResponse execute(String idPatient, String idMedical) {
        return fileRepository.findByIdPatientAndIdMedical(idPatient, idMedical);
    }
}
