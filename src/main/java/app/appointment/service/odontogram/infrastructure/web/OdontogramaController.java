package app.appointment.service.odontogram.infrastructure.web;

import app.appointment.service.odontogram.domain.model.OdontogramaRequest;
import app.appointment.service.odontogram.domain.model.OdontogramaResponse;
import app.appointment.service.odontogram.application.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontogram")
@AllArgsConstructor
public class OdontogramaController {

    private final CreateOdontograma createOdontograma;
    private final FindAllOdontograma findAllOdontograma;
    private final FindOdontogramaById findById;
    private final UpdateOdontogramaById updateOdontogramaById;
    private final DeleteOdontogramaById deleteOdontogramaById;

    // CREATE
    @PostMapping
    public ResponseEntity<OdontogramaResponse> createOdontograma(@RequestBody @Valid OdontogramaRequest patientRequest) {
        return ResponseEntity.of(Optional.of(createOdontograma.execute(patientRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT','ADMIN')")
    @GetMapping
    public ResponseEntity<List<OdontogramaResponse>> getAllOdontogramas() {
        return ResponseEntity.of(Optional.of(findAllOdontograma.execute()));
    }



    // GET BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<OdontogramaResponse> getOdontogramaById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(findById.execute(id)));

    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @PutMapping("/{id}")
    public ResponseEntity<OdontogramaResponse> updateOdontogramaById(@PathVariable String id, @RequestBody @Valid OdontogramaRequest patientRequest) {
        return ResponseEntity.of(Optional.of(updateOdontogramaById.execute(id,patientRequest)));

    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('PATIENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOdontogramaById(@PathVariable String id) {
        deleteOdontogramaById.execute(id);


        return  ResponseEntity.ok().build();

    }
}
