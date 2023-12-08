package app.appointment.service.medical.domain.port;

import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;

import java.util.List;
import java.util.Optional;

public interface MedicalRepository {
    MedicalResponse createNewMedical(MedicalRequest user);
    List<MedicalResponse> getAll();
    void delete(String id) throws Exception;

    MedicalResponse update(String id, MedicalRequest medical) throws Exception;
    void update( MedicalEntity medical);

    Optional<MedicalEntity> findByEmail(String email) ;
    MedicalResponse existEmail(String email) ;
    Optional<MedicalEntity> findByUsername(String username) ;

    MedicalResponse findById(String id) ;

    void updateTwoFactor(MedicalEntity medicalEntity);

    Boolean existUsername(String medical);
}
