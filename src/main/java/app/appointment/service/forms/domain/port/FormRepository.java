package app.appointment.service.forms.domain.port;

import app.appointment.service.forms.domain.model.FormRequest;
import app.appointment.service.forms.domain.model.FormResponse;

import java.util.List;

public interface FormRepository {
    FormResponse save(FormRequest request);

    List<FormResponse> findAll();


    FormResponse findById(String id);

    FormResponse update(String id, FormRequest request);

    void deleteById(String id);

    List<FormResponse>  findByIdMedical(String id);

    List<FormResponse>  findByIdPatient(String id);

    FormResponse findByIdPatientAndIdMedical(String idPatient, String idMedical);

}
