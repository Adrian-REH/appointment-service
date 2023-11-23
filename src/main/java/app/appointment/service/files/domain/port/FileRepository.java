package app.appointment.service.files.domain.port;



import app.appointment.service.files.domain.model.FileRequest;
import app.appointment.service.files.domain.model.FileResponse;

import java.util.List;

public interface FileRepository {
    FileResponse save(FileRequest request);

    List<FileResponse> findAll();


    FileResponse findById(String id);

    FileResponse updateById(String id, FileRequest request);

    void deleteById(String id);

    List<FileResponse>  findByIdMedical(String id);

    List<FileResponse>  findByIdPatient(String id);

    FileResponse findByIdPatientAndIdMedical(String idPatient, String idMedical);
}
