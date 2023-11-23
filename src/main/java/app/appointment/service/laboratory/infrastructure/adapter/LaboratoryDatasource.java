package app.appointment.service.laboratory.infrastructure.adapter;

import app.appointment.service.laboratory.domain.model.LaboratoryResponse;
import app.appointment.service.laboratory.domain.port.LaboratoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LaboratoryDatasource implements LaboratoryRepository {
    @Override
    public LaboratoryResponse findById(String idMedical) {
        return null;
    }
}
