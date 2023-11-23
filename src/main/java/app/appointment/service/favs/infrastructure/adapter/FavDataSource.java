package app.appointment.service.favs.infrastructure.adapter;

import app.appointment.service.favs.domain.model.FavRequest;
import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.favs.domain.port.FavRepository;
import app.appointment.service.favs.infrastructure.adapter.driver.MongoFavRepository;
import app.appointment.service.favs.infrastructure.adapter.driver.entity.FavEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavDataSource implements FavRepository {
    private MongoFavRepository mongoFav;
    private ModelMapper modelMapper;

    @Override
    public FavResponse save(FavRequest medicalRequest) {
        var existEntity = mongoFav.findByIdMedicalAndIdPatient(medicalRequest.getMedical(), medicalRequest.getPatient());
        if (existEntity.isPresent()) {
            throw new ServiceException("Fav exist");
        }
        return modelMapper.map(mongoFav.save(modelMapper.map(medicalRequest, FavEntity.class)), FavResponse.class);
    }

    @Override
    public void delete(String id) {
        mongoFav.deleteById(id);
    }

    @Override
    public List<FavResponse> getAllFavs() {
        return mongoFav.findAll()
                .stream().map(fav -> modelMapper.map(fav, FavResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public FavResponse getById(String id) {

        return modelMapper.map(existFavEntityId(id), FavResponse.class);
    }

    @Override
    public FavResponse getByIdMedical(String id) {
        var optFavEntity = mongoFav.findByIdMedical(id);
        if (optFavEntity.isEmpty()) {
            throw new ServiceException("Fav Medical not found");
        }
        return modelMapper.map(optFavEntity.get(), FavResponse.class);
    }

    @Override
    public FavResponse getByIdPatient(String id) {
        var optFavEntity = mongoFav.findByIdPatient(id);
        if (optFavEntity.isEmpty()) {
            throw new ServiceException("Fav Patient not found");
        }
        return modelMapper.map(optFavEntity.get(), FavResponse.class);
    }

    @Override
    public FavResponse update(String id, FavRequest favRequest) {
        existFavEntityId(id);
        FavEntity entity = modelMapper.map(favRequest, FavEntity.class);
        entity.setId(id);
        return modelMapper.map(mongoFav.save(entity), FavResponse.class);
    }

    @Override
    public FavResponse findByIdMedicalAndPatient(String idPatient, String idMedical) {

        var optResult = mongoFav.findByIdMedicalAndIdPatient(idMedical, idPatient);
        if (optResult.isEmpty()) {
            throw new ServiceException("No existe la relacion");
        }
        return modelMapper.map(optResult.get(), FavResponse.class);
    }

    FavEntity existFavEntityId(String id) {

        if (!mongoFav.existsById(id)) {
            throw new ServiceException("No existe favorite");
        }
        return mongoFav.findById(id).get();
    }
}
