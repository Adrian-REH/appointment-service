package app.appointment.service.specialty.infrastructure.adapter;

import app.appointment.service.specialty.domain.model.SpecialtyRequest;
import app.appointment.service.specialty.domain.model.SpecialtyResponse;
import app.appointment.service.specialty.domain.port.SpecialtyRepository;
import app.appointment.service.specialty.infrastructure.adapter.driver.MongoSpecialtyRepository;
import app.appointment.service.specialty.infrastructure.adapter.driver.entity.SpecialtyEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpecialtyDatasource implements SpecialtyRepository {
    private final MongoSpecialtyRepository mongoSpecialty;
    private final ModelMapper modelMapper;

    @Override
    public SpecialtyResponse save(SpecialtyRequest request) {
        SpecialtyEntity entity = mongoSpecialty.save(modelMapper.map(request, SpecialtyEntity.class));
        return modelMapper.map(entity, SpecialtyResponse.class);
    }

    @Override
    public void delete(String id) {
        if (mongoSpecialty.existsById(id)) {
            throw new ServiceException("No existe la especialidad");
        }
        mongoSpecialty.deleteById(id);
    }

    @Override
    public List<SpecialtyResponse> getAllSpecialtys() {
        return mongoSpecialty
                .findAll()
                .stream()
                .map(specialtyEntity -> modelMapper.map(specialtyEntity, SpecialtyResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyResponse getById(String id) {
        return modelMapper.map(getEntity(id), SpecialtyResponse.class);
    }

    @Override
    public SpecialtyResponse getByIdMedical(String idMedical) {
        Optional<SpecialtyEntity> entity = mongoSpecialty.findByIdMedical(idMedical);
        if (entity.isEmpty()) {
            throw new ServiceException("Id medical not found");
        }

        return modelMapper.map(entity, SpecialtyResponse.class);
    }

    @Override
    public SpecialtyResponse update(String id, SpecialtyRequest request) {

        return modelMapper.map(mongoSpecialty.save(getEntity(id)), SpecialtyResponse.class);
    }

    private SpecialtyEntity getEntity(String id) {
        Optional<SpecialtyEntity> entity = mongoSpecialty.findById(id);
        if(entity.isEmpty()){
            throw new ServiceException("Specialty not find");
        }
        return entity.get();
    }

}
