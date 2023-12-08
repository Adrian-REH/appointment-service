package app.appointment.service.patient.infrastructure.web;

import app.appointment.service.auth.infrastructure.web.security.TokenProvider;
import app.appointment.service.patient.application.*;
import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.utils.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

    private final CreatePatient createPatient;
    private final FindAllPatient findAllPatient;
    private final FindPatientByUsername findByUsername;
    private final FindPatientById findById;
    private final UpdatePatientById updatePatientById;
    private final DeletePatientById deletePatientById;
    private final TokenProvider jwtTokenProvider;

    // CREATE
    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody @Valid PatientRequest patientRequest) {


        return ResponseEntity.of(Optional.of(createPatient.execute(patientRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT','ADMIN')")
    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients(HttpServletRequest request) {
        final String username = AppUtil.jwtGetUsername(request, jwtTokenProvider);
        final String role = AppUtil.jwtGetRole(request, jwtTokenProvider);

        return ResponseEntity.of(Optional.of(findAllPatient.execute(username,role)));
    }

    // GET BY EMAIL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT')")
    @GetMapping("/username/{username}")
    public ResponseEntity<PatientResponse> getPatientByEmail(@PathVariable String username) {
        return ResponseEntity.of(Optional.of(findByUsername.execute(username)));

    }

    // GET BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(findById.execute(id)));

    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatientById(@PathVariable String id, @RequestBody @Valid PatientRequest patientRequest) {
        return ResponseEntity.of(Optional.of(updatePatientById.execute(id,patientRequest)));

    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity deletePatientById(@PathVariable String id) {
        deletePatientById.execute(id);
        return  ResponseEntity.ok().build();
    }
}
