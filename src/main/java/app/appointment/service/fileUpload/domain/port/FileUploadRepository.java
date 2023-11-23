package app.appointment.service.fileUpload.domain.port;

import app.appointment.service.fileUpload.domain.model.GridFSResponse;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadRepository {
    String uploadFile(MultipartFile file, String id);

    GridFSResponse getFile(String fileId);

    boolean deleteFile(String fileId);
}
