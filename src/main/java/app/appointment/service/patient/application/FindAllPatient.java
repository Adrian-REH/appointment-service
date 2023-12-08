package app.appointment.service.patient.application;

import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.favs.domain.port.FavRepository;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindAllPatient {

    private final PatientRepository patientRepository;
    private final FavRepository favRepository;
    private final ModelMapper modelMapper;
    /**
     * El objetivo es que si lo consulta un MEDICO devolvera una lista de todos los pacientes asociados en favs
     * EN caso de ser PATIENT entonces devolvera una lista con solo su dato.
     * (En este caso hay que ser precavidos y devolvera paginacion. Pero se vera luego.)
     * @param username
     * @param role
     * @return
     */
    public List<PatientResponse> execute(String username, String role) {
        List<PatientResponse> responses = new ArrayList<>();
        if(role.equals("MEDICAL")){
            final List<String > listPatients = favRepository.getAllFavs(username).stream().map(FavResponse::getUsernameMedical).toList();

            responses.addAll(patientRepository.findAll().stream().filter(patientResponse -> listPatients.contains(patientResponse.getUsername())).toList());
        }else if(role.equals("PATIENT")){
            var opt = patientRepository.findByUsername(username);
            if(opt.isEmpty()){
                throw new ServiceException(600,"No se encontro el username");
            }
            final PatientResponse patientResponse = modelMapper.map(opt.get(), PatientResponse.class);

            responses.add(patientResponse);
        }
        return responses;
    }
}
