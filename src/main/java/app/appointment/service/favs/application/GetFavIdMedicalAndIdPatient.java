package app.appointment.service.favs.application;

import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.favs.domain.port.FavRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetFavIdMedicalAndIdPatient {
    private final FavRepository favRepository;
    public FavResponse execute(String idPatient, String idMedical) {
        return  favRepository.findByIdMedicalAndPatient(idPatient, idMedical);
    }

}
