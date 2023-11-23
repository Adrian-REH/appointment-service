package app.appointment.service.files.infrastructure.adapter;

import app.appointment.service.files.domain.model.FileRequest;
import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import app.appointment.service.files.infrastructure.adapter.driver.MongoFileRepository;
import app.appointment.service.files.infrastructure.adapter.driver.entity.FileEntity;
import app.appointment.service.forms.infrastructure.adapter.driver.entity.FormEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileDatasource implements FileRepository {
    private final MongoFileRepository mongoFile;
    private final ModelMapper modelMapper;


    @Override
    public FileResponse save(FileRequest request) {

        return modelMapper.map(mongoFile.save(modelMapper.map(request, FileEntity.class)), FileResponse.class);
    }

    @Override
    public List<FileResponse> findAll() {
        return mongoFile
                .findAll()
                .stream()
                .map(value-> modelMapper.map(value, FileResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public FileResponse findById(String id) {
        getFileEntity(id);
        return modelMapper.map(getFileEntity(id), FileResponse.class);
    }

    private FileEntity getFileEntity(String id) {
        Optional<FileEntity> entity = mongoFile.findById(id);
        if (entity.isEmpty()){
            throw new ServiceException("No existe el archivo");
        }
        return entity.get();
    }

    @Override
    public FileResponse updateById(String id, FileRequest request) {
        getFileEntity(id);

        FileEntity entity = modelMapper.map(request, FileEntity.class);
        entity.setId(id);
        mongoFile.save(entity);

        return modelMapper.map(entity, FileResponse.class);
    }

    @Override
    public void deleteById(String id) {
        getFileEntity(id);
        mongoFile.deleteById(id);
    }

    @Override
    public List<FileResponse> findByIdMedical(String id) {
        if(mongoFile.findByIdMedical(id).isEmpty()){
            throw new ServiceException("No existe archivos que contengan un medico");
        }

        return mongoFile.findAllByIdMedical(id).stream().map(file ->modelMapper.map(file, FileResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<FileResponse>  findByIdPatient(String id) {

        if(mongoFile.findByIdPatient(id).isEmpty()){
            throw new ServiceException("No existe archivos que contengan un medico");
        }
        return mongoFile.findAllByIdPatient(id).stream().map(file ->modelMapper.map(file, FileResponse.class)).collect(Collectors.toList());
    }

    @Override
    public FileResponse findByIdPatientAndIdMedical(String idPatient, String idMedical) {
        Optional<FileEntity> optFileEntity = mongoFile.findByIdPatientAndIdMedical(idPatient,idMedical);
        if (optFileEntity.isEmpty()){
            throw new ServiceException("No existe la relacion para este archivo");
        }
        return modelMapper.map(optFileEntity.get(), FileResponse.class);
    }
}
