package app.appointment.service.fileUpload.application;

import app.appointment.service.fileUpload.domain.port.FileUploadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteFileUpload {
    private final FileUploadRepository uploadService;

    public boolean execute(String file) {
        return  uploadService.deleteFile(file);
    }
}
