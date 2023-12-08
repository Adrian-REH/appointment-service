package app.appointment.service.patient.application;

import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPatientByUsername {
    private final ModelMapper modelMapper;

    private final PatientRepository patientRepository;
    public PatientResponse execute(String username) {
        var opt = patientRepository.findByUsername(username);
        if(opt.isEmpty()){
            throw new ServiceException(600,"No se encontro el username");
        }
        return modelMapper.map(opt.get(), PatientResponse.class);

    }
}
