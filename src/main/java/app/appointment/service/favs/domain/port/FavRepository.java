package app.appointment.service.favs.domain.port;

import app.appointment.service.favs.domain.model.FavRequest;
import app.appointment.service.favs.domain.model.FavResponse;

import java.util.List;

public interface FavRepository {
    FavResponse save(final FavRequest medicalRequest);

    void delete(final String id);

    List<FavResponse> getAllFavs();

    FavResponse getById(final String id);

    FavResponse getByIdMedical(final String id);
    FavResponse getByIdPatient(final String id);

    FavResponse update(final String id,final FavRequest medicalEntity);

    FavResponse findByIdMedicalAndPatient(final String idPatient,final String idMedical);
}
