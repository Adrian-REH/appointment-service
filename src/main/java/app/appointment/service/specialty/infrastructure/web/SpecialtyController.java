package app.appointment.service.specialty.infrastructure.web;

import app.appointment.service.specialty.application.*;
import app.appointment.service.specialty.domain.model.SpecialtyRequest;
import app.appointment.service.specialty.domain.model.SpecialtyResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/specialty")
public class SpecialtyController {

    private final CreateSpecialty createSpecialty;
    private final FindAllSpecialty findAllSpecialty;
    private final FindSpecialtyById findById;
    private final UpdateSpecialtyById updateSpecialtyById;
    private final DeleteSpecialtyById deleteSpecialtyById;

    // CREATE
    @PostMapping
    public ResponseEntity<SpecialtyResponse> createSpecialty(@RequestBody @Valid SpecialtyRequest patientRequest) {
        return ResponseEntity.of(Optional.of(createSpecialty.execute(patientRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT','ADMIN')")
    @GetMapping
    public ResponseEntity<List<SpecialtyResponse>> getAllSpecialtys() {
        return ResponseEntity.of(Optional.of(findAllSpecialty.execute()));
    }


    // GET BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecialtyById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(findById.execute(id)));

    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSpecialtyById(@PathVariable String id, @RequestBody @Valid SpecialtyRequest patientRequest) {
        return ResponseEntity.of(Optional.of(updateSpecialtyById.execute(id,patientRequest)));

    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSpecialtyById(@PathVariable String id) {
        deleteSpecialtyById.execute(id);
        return  ResponseEntity.ok().build();
    }
}
