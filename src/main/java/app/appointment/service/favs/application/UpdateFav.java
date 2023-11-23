package app.appointment.service.favs.application;

import app.appointment.service.favs.domain.model.FavRequest;
import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.favs.domain.port.FavRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.port.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateFav {
    private final FavRepository favRepository;
    private final MedicalRepository medicalRepository;
    private final PatientRepository patientRepository;
    public FavResponse execute(String id, FavRequest favRequest) {
        medicalRepository.findByEmail(favRequest.getMedical());
        patientRepository.findByEmail(favRequest.getPatient());
        favRepository.getById(id);


        return favRepository.update(id, favRequest);
    }
}
