package app.appointment.service.medical.application;

import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.favs.domain.port.FavRepository;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllMedical {

    private final MedicalRepository medicalRepository;
    private final ModelMapper modelMapper;

    /**
     * El objetivo es que si soy un medico entonces me debe devolver una lista con solo la informacion del propio medico
     * en caso de ser un paciente debe devolve runa lista con todos los medicos que existan en la base de datos.
     * (En este caso hay que ser precavidos y devolvera paginacion. Pero se vera luego.)
     * @param username
     * @param role
     * @return
     */
    public List<MedicalResponse> execute(String username, String role) {
        List<MedicalResponse> responses = new ArrayList<>();

        if (Objects.equals(role, "MEDICAL")){
            var opt = medicalRepository.findByUsername(username);
            if(opt.isEmpty()){
                throw new ServiceException(600,"No se encontro el username");
            }
             var medicalResponse = modelMapper.map(opt.get(), MedicalResponse.class);
            responses.add(medicalResponse);
        }else if (Objects.equals(role, "PATIENT")){
            responses =  medicalRepository.getAll();
        }

        //Si soy un paciente
        return responses;
    }
}
