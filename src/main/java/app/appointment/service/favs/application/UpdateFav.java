package app.appointment.service.favs.application;

import app.appointment.service.favs.domain.model.FavRequest;
import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.favs.domain.port.FavRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateFav {
    private final FavRepository favRepository;
    private final MedicalRepository medicalRepository;
    private final PatientRepository patientRepository;
    public FavResponse execute(String id, FavRequest favRequest) {
        var medical = medicalRepository.existUsername(favRequest.getUsernameMedical());
        var patient = patientRepository.existUsername(favRequest.getUsernamePatient());

        if(patient && medical){
            throw new ServiceException(601,"Fav already not exists");
        }
        favRepository.getById(id);


        return favRepository.update(id, favRequest);
    }
}
