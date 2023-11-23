package app.appointment.service.odontogram.domain.port;


import app.appointment.service.odontogram.domain.model.OdontogramaRequest;
import app.appointment.service.odontogram.domain.model.OdontogramaResponse;

import java.util.List;

public interface OdontogramaRepository {
    OdontogramaResponse save(OdontogramaRequest patientRequest);

    List<OdontogramaResponse> findAll();

    OdontogramaResponse findById(String id);

    OdontogramaResponse update( OdontogramaRequest patientRequest);

    void deleteById(String id);
}
