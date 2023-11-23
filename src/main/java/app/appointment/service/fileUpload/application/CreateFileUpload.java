package app.appointment.service.fileUpload.application;

import app.appointment.service.fileUpload.domain.port.FileUploadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class CreateFileUpload {
    private final FileUploadRepository uploadService;


    public String execute(MultipartFile file, String id) {
        return uploadService.uploadFile(file,id);
    }
}
