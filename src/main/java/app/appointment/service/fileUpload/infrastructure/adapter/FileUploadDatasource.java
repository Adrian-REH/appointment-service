package app.appointment.service.fileUpload.infrastructure.adapter;

import app.appointment.service.fileUpload.domain.model.GridFSResponse;
import app.appointment.service.fileUpload.domain.port.FileUploadRepository;
import app.appointment.service.utils.exception.ServiceException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class FileUploadDatasource implements FileUploadRepository {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    //TODO USAR ESTA FUNCION COMBINADA CON OTROS COMPONENTES PARA SUBIR ARCHIVOS

    @Override
    public String uploadFile(MultipartFile file, String id) {
        try {
            InputStream inputStream = file.getInputStream();

            BasicDBObject metaData = new BasicDBObject();
            metaData.put("contentType", file.getContentType());
            metaData.put("owner", id);
            ObjectId fileId = gridFsTemplate.store(inputStream, file.getOriginalFilename(), metaData);

            return fileId.toString();
        } catch (Exception e) {
            return "Error al subir el archivo: " + e.getMessage();
        }
    }
    @Override
    public GridFSResponse getFile(String fileId) {
        GridFSResponse gridFSResponse = new GridFSResponse();

        gridFSResponse.setGridFSFile(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId))));

        if (gridFSResponse.getGridFSFile()==null) {
            throw new ServiceException("Error al buscar el archivo: ");
        }
        gridFSResponse.setGridFsResource(gridFsTemplate.getResource(gridFSResponse.getGridFSFile().getFilename()));


        if (gridFSResponse.getGridFsResource().exists()) {
            return gridFSResponse;
        } else {
            return null; // El archivo no existe
        }

    }
    @Override
    public boolean deleteFile(String fileId) {
        try {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId)));
            return true; // Eliminación exitosa
        } catch (Exception e) {
            return false; // Error al eliminar el archivo
        }
    }
}
