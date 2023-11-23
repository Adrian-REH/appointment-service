package app.appointment.service.laboratory.domain.port;

import app.appointment.service.laboratory.domain.model.LaboratoryRequest;
import app.appointment.service.laboratory.domain.model.LaboratoryResponse;

public interface LaboratoryRepository {
    LaboratoryResponse findById(String idMedical);
}
