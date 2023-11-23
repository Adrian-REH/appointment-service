package app.appointment.service.fileUpload.application;

import app.appointment.service.fileUpload.domain.model.GridFSResponse;
import app.appointment.service.fileUpload.domain.port.FileUploadRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOneFileUpload {
    private final FileUploadRepository uploadService;


    public GridFSResponse execute(String file) {
        return uploadService.getFile(file);
    }
}
