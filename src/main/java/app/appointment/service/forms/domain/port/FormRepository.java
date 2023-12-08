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

    List<FormResponse> findByUsernameMedical(String id);

    List<FormResponse> findByUsernamePatient(String usernamePatient);

    FormResponse findByUsernamePatientAndUsernameMedical(String usernamePatient, String usernameMedical);

}
