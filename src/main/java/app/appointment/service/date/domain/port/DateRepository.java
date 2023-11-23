package app.appointment.service.date.domain.port;

import app.appointment.service.date.domain.model.DateRequest;
import app.appointment.service.date.domain.model.DateResponse;
import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;

import java.util.List;

public interface DateRepository {

    DateResponse save(DateRequest dateRequest);

    List<DateResponse> findAll();


    DateResponse findById(String id);

    DateResponse updateById(String id, DateRequest dateRequest);

    void deleteById(String id);


}
