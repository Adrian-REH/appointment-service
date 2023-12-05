package app.appointment.service.medical.application;

import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GetByEmailMedical {
    private final ModelMapper modelMapper;

    private final MedicalRepository medicalRepository;
    public MedicalResponse execute(String email) {
            var opt = medicalRepository.findByUsername(email);
        if(opt.isEmpty()){
            throw new ServiceException(600,"No se encontro el username");
        }
        return modelMapper.map(opt.get(), MedicalResponse.class);

    }
}
