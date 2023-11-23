package app.appointment.service.medical.infrastructure.web;

import app.appointment.service.medical.application.*;
import app.appointment.service.medical.domain.model.MedicalRequest;
import app.appointment.service.medical.domain.model.MedicalResponse;
import app.appointment.service.medical.infrastructure.adapter.driver.entity.MedicalEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicals")
@AllArgsConstructor
public class MedicalController {

    private final CreateMedical createMedical;
    private final GetAllMedical getAllMedical;
    private final GetOneMedical getOneMedical;
    private final GetByEmailMedical getByEmailMedical;
    private final UpdateMedical updateMedical;
    private final DeleteMedical deleteMedical;

    // CREATE
    @PostMapping
    public ResponseEntity<MedicalResponse> createMedical(@RequestBody @Valid MedicalRequest medicalRequest) {
        return ResponseEntity.of(Optional.of(createMedical.execute(medicalRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<MedicalResponse>> getAllMedicals() {
        return ResponseEntity.of(Optional.of(getAllMedical.execute()));

    }

    // GET BY EMAIL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT')")
    @GetMapping("/email/{email}")
    public ResponseEntity<MedicalResponse> getMedicalByEmail(@PathVariable String email) {

        MedicalResponse response = new MedicalResponse();
            response = getByEmailMedical.execute(email);

        return ResponseEntity.of(Optional.of(response));

    }

    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalResponse> getMedicalById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getOneMedical.execute(id)));
    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalResponse> updateMedical(@PathVariable String id, @Valid @RequestBody MedicalRequest medicalEntity) {
        return ResponseEntity.of(Optional.of(updateMedical.execute(id,medicalEntity)));
    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MedicalResponse> deleteMedical(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(deleteMedical.execute(id)));

    }
}
