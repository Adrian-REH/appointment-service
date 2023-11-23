package app.appointment.service.medical.application;

import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GetByEmailMedical {

    private final MedicalRepository medicalRepository;
    public MedicalResponse execute(String email)  {
        MedicalResponse response = new MedicalResponse();

        response = medicalRepository.findByEmail(email);

        return response;
    }
}
