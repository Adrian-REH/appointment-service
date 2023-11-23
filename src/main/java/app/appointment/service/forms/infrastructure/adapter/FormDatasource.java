package app.appointment.service.forms.infrastructure.adapter;

import app.appointment.service.forms.domain.model.FormRequest;
import app.appointment.service.forms.domain.model.FormResponse;
import app.appointment.service.forms.domain.port.FormRepository;
import app.appointment.service.forms.infrastructure.adapter.driver.MongoFormRepository;
import app.appointment.service.forms.infrastructure.adapter.driver.entity.FormEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FormDatasource implements FormRepository {
    private MongoFormRepository mongoFormRepository;
    private ModelMapper modelMapper;
    @Override
    public FormResponse save(FormRequest request) {

        return modelMapper.map(mongoFormRepository.save(modelMapper.map(request, FormEntity.class)), FormResponse.class);
    }

    @Override
    public List<FormResponse> findAll() {
        return mongoFormRepository
                .findAll()
                .stream()
                .map(value -> modelMapper.map(value, FormResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public FormResponse findById(String id) {
        return modelMapper.map(getForm(id), FormResponse.class);
    }

    private FormEntity getForm(String id) {
        Optional<FormEntity> entity = mongoFormRepository.findById(id);
        if(entity.isEmpty()) {
            throw new ServiceException("No existe el formulario");
        }
        return entity.get();
    }

    @Override
    public FormResponse update(String id, FormRequest request) {
        getForm(id);
        var entity = modelMapper.map(request, FormEntity.class);
        entity.setId(id);
        entity = mongoFormRepository.save(entity);
        return modelMapper.map(entity, FormResponse.class);
    }

    @Override
    public void deleteById(String id) {
        getForm(id);
        mongoFormRepository.deleteById(id);
    }

    @Override
    public List<FormResponse> findByIdMedical(String id) {
        if(mongoFormRepository.findByIdMedical(id).isEmpty()){
            throw new ServiceException("No existe el formulario para el medico");
        }

        return mongoFormRepository
                .findAllByIdMedical(id)
                .stream()
                .map(value -> modelMapper.map(value,FormResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FormResponse> findByIdPatient(String id) {

        if(mongoFormRepository.findByIdPatient(id).isEmpty()){
            throw new ServiceException("No existe el formulario para el paciente");
        }

        return mongoFormRepository
                .findAllByIdPatient(id)
                .stream()
                .map(value -> modelMapper.map(value,FormResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public FormResponse findByIdPatientAndIdMedical(String idPatient, String idMedical) {
        Optional<FormEntity> entity = mongoFormRepository.findByIdPatientAndIdMedical(idPatient, idMedical);
        if(entity.isEmpty()){
            throw new ServiceException("No existe el formulario para el paciente");
        }

        return modelMapper.map(entity.get(), FormResponse.class);
    }
}
