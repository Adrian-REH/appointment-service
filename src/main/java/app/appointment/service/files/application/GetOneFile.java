package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOneFile {
    private final FileRepository fileRepository;
    public FileResponse execute(String id) {
        return fileRepository.findById(id);
    }
}
