package app.appointment.service.medical.domain.port;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;

import java.util.List;
import java.util.Optional;

public interface MedicalRepository {
    MedicalResponse save(MedicalRequest user);
    List<MedicalResponse> getAll();
    void delete(String id) throws Exception;

    MedicalResponse update(String id, MedicalRequest medical) throws Exception;

    MedicalResponse findByEmail(String email) ;
    Optional<MedicalEntity> findByUsername(String username) ;

    MedicalResponse findById(String id) ;
}
