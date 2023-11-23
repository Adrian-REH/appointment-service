package app.appointment.service.specialty.domain.port;


import app.appointment.service.specialty.domain.model.SpecialtyRequest;
import app.appointment.service.specialty.domain.model.SpecialtyResponse;

import java.util.List;

public interface SpecialtyRepository {
    SpecialtyResponse save(final SpecialtyRequest medicalRequest);

    void delete(final String id);

    List<SpecialtyResponse> getAllSpecialtys();

    SpecialtyResponse getById(final String id);

    SpecialtyResponse getByIdMedical(final String id);

    SpecialtyResponse update(final String id,final SpecialtyRequest medicalEntity);


}
