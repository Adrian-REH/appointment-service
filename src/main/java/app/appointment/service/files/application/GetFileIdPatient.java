package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetFileIdPatient {
    private final FileRepository fileRepository;

    public List<FileResponse> execute(String idPatient) {
        return fileRepository.findByIdPatient(idPatient);
    }
}
