package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteFile {
    private final FileRepository fileRepository;
    public void execute(String id) {
        fileRepository.deleteById(id);
    }
}
