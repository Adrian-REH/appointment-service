package app.appointment.service.medical.infrastructure.adapter;

import app.appointment.service.auth.domain.port.RoleService;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.domain.model.Schedule;
import app.appointment.service.medical.domain.model.Specialty;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.MongoMedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.infrastructure.adapter.driver.MongoPatientRepository;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalDatasource implements MedicalRepository {
    private BCryptPasswordEncoder bcryptEncoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final MongoMedicalRepository medicalRepository;
    private final MongoPatientRepository patientRepository;

    @Override
    public MedicalResponse createNewMedical(MedicalRequest medicalRequest) {

        //Check 0: Username NotExist in Patient and Medical
        if (medicalRepository.existsByUsername(medicalRequest.getUsername()) && patientRepository.existsByUsername(medicalRequest.getUsername())) {
            throw new ServiceException(600,"El nombre de usuario existe");
        }
        // Check 1: ParamIsBlank
        if (medicalRequest.getPassword().isBlank() || medicalRequest.getEmail().isBlank())
            throw new ServiceException(600,"Ingrese una clave, usuario y/ email");

        // Check 2: email
        if (medicalRepository.existsByEmail(medicalRequest.getEmail()))
            throw new ServiceException(600,"Email ocupado");

        medicalRequest.setPassword(bcryptEncoder.encode(medicalRequest.getPassword()));


        MedicalEntity medicalEntity = modelMapper.map(medicalRequest, MedicalEntity.class);
        medicalEntity.setEmailVerified(false);
        Role role = roleService.findByName("MEDICAL");

        medicalEntity.setListSchedule(new ArrayList<>(List.of( Schedule.builder()
                        .monday("09:00 de 15:00")
                        .tuesday("09:00 de 15:00")
                        .wednesday("09:00 de 15:00")
                        .thursday("09:00 de 15:00")
                        .friday("09:00 de 15:00")
                        .saturday("09:00 de 15:00")
                        .sunday("09:00 de 15:00")
                        .createdAt(new Date())
                .build())));
        medicalEntity.setListSpecialty(new ArrayList<>(List.of( Specialty.builder()
                .createdAt(new Date()).build())));
        medicalEntity.setRole(role);

        medicalRepository.save(modelMapper.map(medicalEntity, MedicalEntity.class));

        return modelMapper.map(medicalEntity, MedicalResponse.class);
    }

    @Override
    public List<MedicalResponse> getAll() {


        var result = medicalRepository.findAll();
        return result.stream()
                .map(medical -> modelMapper.map(medical, MedicalResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws Exception {
        Optional<MedicalEntity> medical = medicalRepository.findById(id);
        if (medical.isEmpty()) {
            throw new ServiceException("No se encontro el medico");
        }
        medicalRepository.delete(medical.get());

    }

    @Override
    public MedicalResponse update(String id, MedicalRequest medical) throws Exception {
        //TODO Verificar si me dan el tuition si es correcto.
        //TODO en caso de ser correcto enviar un email de confirmacion.
        if (!medicalRepository.existsById(id)) {
            throw new ServiceException("No se encontro el ID");
        }
        MedicalEntity medicalEntity = modelMapper.map(medical, MedicalEntity.class);
        medicalEntity.setId(id);

        medicalRepository.save(medicalEntity);

        return modelMapper.map(medicalEntity, MedicalResponse.class);
    }

    @Override
    public void update(MedicalEntity medical) {
        medicalRepository.save(medical);
    }

    @Override
    public MedicalResponse findById(String id) {
        if (!medicalRepository.existsById(id)) {
            throw new ServiceException("User id no existe");

        }
        return modelMapper.map(medicalRepository.findById(id).get(), MedicalResponse.class);
    }

    @Override
    public void updateTwoFactor(MedicalEntity medicalEntity) {
        medicalRepository.save(medicalEntity);
    }

    @Override
    public Boolean existUsername(String username) {
        return medicalRepository.existsByUsername(username);
    }

    @Override
    public Optional<MedicalEntity> findByEmail(String email) {
        return medicalRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public MedicalResponse existEmail(String email) {
        if (!medicalRepository.existsByEmail(email)) {
            throw new ServiceException("No se encontro el email");

        }

        return modelMapper.map(medicalRepository.findByEmailIgnoreCase(email).get(), MedicalResponse.class);
    }

    @Override
    public Optional<MedicalEntity> findByUsername(String username) {
        return medicalRepository.findByUsername(username);
    }
}
