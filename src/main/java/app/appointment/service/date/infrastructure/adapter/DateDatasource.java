package app.appointment.service.date.infrastructure.adapter;

import app.appointment.service.date.domain.model.DateRequest;
import app.appointment.service.date.domain.model.DateResponse;
import app.appointment.service.date.domain.port.DateRepository;
import app.appointment.service.date.infrastructure.adapter.driver.MongoDateRepository;
import app.appointment.service.date.infrastructure.adapter.driver.entity.DateEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DateDatasource implements DateRepository {
    private final ModelMapper modelMapper;
    private final MongoDateRepository mongoDateRepository;
    @Override
    public DateResponse save(DateRequest dateRequest) {
        return modelMapper.map(mongoDateRepository.save(modelMapper.map(dateRequest, DateEntity.class)),DateResponse.class);
    }

    @Override
    public List<DateResponse> findAll() {
        return mongoDateRepository
                .findAll()
                .stream()
                .map(value -> modelMapper.map(value, DateResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public DateResponse findById(String id) {
        return modelMapper.map(getDateEntity(id),DateResponse.class);
    }

    @Override
    public DateResponse updateById(String id, DateRequest dateRequest) {
        getDateEntity(id);

        DateEntity dateEntity = modelMapper.map(dateRequest, DateEntity.class);
        dateEntity.setId(id);

        return modelMapper.map(mongoDateRepository.save(dateEntity),DateResponse.class);
    }

    @Override
    public void deleteById(String id) {
        getDateEntity(id);
        mongoDateRepository.deleteById(id);
    }

    private DateEntity getDateEntity(String id) {
        Optional<DateEntity> dateEntity = mongoDateRepository.findById(id);
        if (dateEntity.isEmpty()) {
            throw new ServiceException("No existe Date id");
        }
        return dateEntity.get();
    }
}
