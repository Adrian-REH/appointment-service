package app.appointment.service.forms.infrastructure.web;

import app.appointment.service.forms.application.*;
import app.appointment.service.forms.domain.model.FormRequest;
import app.appointment.service.forms.domain.model.FormResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequestMapping(value ="/forms")
public class FormController {
    private final CreateForm createForm;
    private final GetAllForm getAllForm;
    private final GetOneForm getOneForm;
    private final GetFormIdMedical getFormIdMedical;
    private final GetFormIdPatient getFormIdPatient;
    private final GetFormIdPatientAndIdMedical getFormIdPatientAndIdMedical;
    private final UpdateForm updateForm;
    private final DeleteForm deleteForm;

    // CREATE
    @PostMapping
    public ResponseEntity<FormResponse> createForm(@RequestBody @Valid FormRequest medicalRequest) {
        return ResponseEntity.of(Optional.of(createForm.execute(medicalRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<FormResponse>> getAllForms() {
        return ResponseEntity.of(Optional.of(getAllForm.execute()));

    }

    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<FormResponse>> getFormPatient(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getFormIdPatient.execute(id)));
    }
    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/patient/{idPatient}/medical/{idMedical}")
    public ResponseEntity<FormResponse> getFormPatient(@PathVariable String idPatient, @PathVariable String idMedical) {
        return ResponseEntity.of(Optional.of(getFormIdPatientAndIdMedical.execute(idPatient, idMedical)));
    }
    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/medical/{id}")
    public ResponseEntity<List<FormResponse>> getFormMedical(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getFormIdMedical.execute(id)));
    }
    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FormResponse> getFormById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getOneForm.execute(id)));
    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @PutMapping("/{id}")
    public ResponseEntity<FormResponse> updateForm(@PathVariable String id, @Valid @RequestBody FormRequest medicalEntity) {
        return ResponseEntity.of(Optional.of(updateForm.execute(id,medicalEntity)));
    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteForm(@PathVariable String id) {
        deleteForm.execute(id);
        return ResponseEntity.ok().build();

    }
}
