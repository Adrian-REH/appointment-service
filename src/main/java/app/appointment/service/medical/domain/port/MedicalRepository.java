package app.appointment.service.medical.domain.port;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;

import java.util.List;

public interface MedicalRepository {
    MedicalResponse save(MedicalRequest user);
    List<MedicalResponse> getAll();
    void delete(String id) throws Exception;

    MedicalResponse update(String id, MedicalRequest medical) throws Exception;

    MedicalResponse findByEmail(String email) ;

    MedicalResponse findById(String id) ;
}
