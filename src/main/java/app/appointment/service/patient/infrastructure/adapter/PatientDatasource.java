package app.appointment.service.patient.infrastructure.adapter;

import app.appointment.service.auth.domain.exception.IdAlreadyNotExistsException;
import app.appointment.service.auth.domain.port.RoleService;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.infrastructure.adapter.driver.MongoMedicalRepository;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.patient.domain.port.PatientRepository;
import app.appointment.service.patient.infrastructure.adapter.driver.MongoPatientRepository;
import app.appointment.service.patient.infrastructure.adapter.driver.entity.PatientEntity;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientDatasource implements PatientRepository {
    private BCryptPasswordEncoder bcryptEncoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final MongoMedicalRepository medicalRepository;
    private final MongoPatientRepository patientRepository;

    @Override
    public PatientResponse save(PatientRequest patientRequest) {
        //Check 0: Username NotExist in Patient and Medical
        if (patientRepository.existsByUsername(patientRequest.getUsername()) && medicalRepository.existsByUsername(patientRequest.getUsername())) {
            throw new ServiceException("El nombre de usuario existe");
        }
        // Check 1: ParamIsBlank
        if (patientRequest.getPassword().isBlank() || patientRequest.getEmail().isBlank())
            throw new ServiceException("Ingrese una clave, usuario y/ email");

        // Check 2: email
        if (patientRepository.existsByEmail(patientRequest.getEmail()))
            throw new ServiceException("Email ocupado");

        patientRequest.setPassword(bcryptEncoder.encode(patientRequest.getPassword()));


        PatientEntity patientEntity = modelMapper.map(patientRequest, PatientEntity.class);

        Role role = roleService.findByName("ADMIN");

        if (patientEntity.getEmail().split("@")[1].equals("appointment.com")) {
            role = roleService.findByName("ATM");
        }

        patientEntity.setRole(role);

        patientRepository.save(modelMapper.map(patientEntity, PatientEntity.class));

        return modelMapper.map(patientEntity, PatientResponse.class);
    }

    @Override
    public List<PatientResponse> findAll() {
        var result = patientRepository.findAll();
        return result.stream()
                .map(medical -> modelMapper.map(medical, PatientResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse findByEmail(String email) {
        if (!patientRepository.existsByEmail(email)) {
            throw new ServiceException("No se encontro el email");

        }

        return modelMapper.map(patientRepository.findByEmail(email).get(), PatientResponse.class);
    }

    @Override
    public PatientResponse findById(String id) {
        if (!patientRepository.existsById(id)) {
            throw new ServiceException("No se encontro el paciente");

        }
        return modelMapper.map(patientRepository.findById(id).get(), PatientResponse.class);
    }

    @Override
    public PatientResponse updateById(String id, PatientRequest patientRequest) {
        if (!patientRepository.existsById(id)) {
            throw new ServiceException("No se encontro el ID");
        }
        PatientEntity patientEntity = modelMapper.map(patientRequest, PatientEntity.class);
        patientEntity.setId(id);

        patientRepository.save(patientEntity);

        return modelMapper.map(patientEntity, PatientResponse.class);
    }

    @Override
    public void deleteById(String id) {
        Optional<PatientEntity> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            throw new ServiceException("No se encontro el medico");
        }
        patientRepository.delete(patient.get());
    }
}
