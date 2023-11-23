package app.appointment.service.fileUpload.domain.model;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsResource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GridFSResponse {
    private GridFsResource gridFsResource;
    private GridFSFile gridFSFile;
}
