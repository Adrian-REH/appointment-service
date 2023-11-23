package app.appointment.service.odontogram.infrastructure.adapter;

import app.appointment.service.odontogram.domain.model.OdontogramaRequest;
import app.appointment.service.odontogram.domain.model.OdontogramaResponse;
import app.appointment.service.odontogram.domain.port.OdontogramaRepository;
import app.appointment.service.odontogram.infrastructure.adapter.driver.MongoOdontogramaRepository;
import app.appointment.service.odontogram.infrastructure.adapter.driver.entity.OdontogramaEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class OdontogramaDatasource implements OdontogramaRepository {
    private final MongoOdontogramaRepository mongoOdontograma;
    private final ModelMapper modelMapper;
    @Override
    public OdontogramaResponse save(OdontogramaRequest patientRequest) {
        var result =  mongoOdontograma.save(modelMapper.map(patientRequest, OdontogramaEntity.class));
        return  modelMapper.map(result, OdontogramaResponse.class);
    }

    @Override
    public List<OdontogramaResponse> findAll() {
        return mongoOdontograma.findAll().stream().map(value -> modelMapper.map(value, OdontogramaResponse.class)).toList();
    }

    @Override
    public OdontogramaResponse findById(String id) {
        if (mongoOdontograma.existsById(id)) {
            throw new ServiceException("No existe odontograma");
        }

        return modelMapper.map(mongoOdontograma.findById(id), OdontogramaResponse.class);
    }

    @Override
    public OdontogramaResponse update(OdontogramaRequest patientRequest) {
        var result = mongoOdontograma.save(modelMapper.map(patientRequest, OdontogramaEntity.class));
        return  modelMapper.map(result, OdontogramaResponse.class);
    }

    @Override
    public void deleteById(String id) {
        mongoOdontograma.deleteById(id);
    }
}
