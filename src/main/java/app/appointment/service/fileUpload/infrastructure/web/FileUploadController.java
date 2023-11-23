package app.appointment.service.fileUpload.infrastructure.web;

import app.appointment.service.fileUpload.application.CreateFileUpload;
import app.appointment.service.fileUpload.application.DeleteFileUpload;
import app.appointment.service.fileUpload.application.GetOneFileUpload;
import app.appointment.service.fileUpload.domain.model.GridFSResponse;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/controller")
@Slf4j
public class FileUploadController {


    private CreateFileUpload createFileUpload;
    private GetOneFileUpload getOneFileUpload;
    private DeleteFileUpload deleteFileUpload;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @PostMapping("/{idOwner}")
    @ApiImplicitParam(name = "myFile", value = "Archivo a subir", required = true, dataType = "file", paramType = "form")
    public String uploadFile(@RequestParam("myFile") MultipartFile file,@PathVariable String idOwner) {
        return createFileUpload.execute(file,idOwner);
    }


    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileId) throws IOException {
        GridFSResponse resource = getOneFileUpload.execute(fileId);


        // Configurar las cabeceras para mostrar el archivo en línea
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(resource.getGridFSFile().getMetadata().getString("contentType")));
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(resource.getGridFSFile().getFilename()).build());

        // Leer el contenido del archivo
        byte[] content;
        try (InputStream inputStream = resource.getGridFsResource().getInputStream()) {
            content = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            // Manejar errores de lectura del archivo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity deleteFile(@PathVariable String fileId) {
        deleteFileUpload.execute(fileId);
        return ResponseEntity.ok().build();
    }
}
