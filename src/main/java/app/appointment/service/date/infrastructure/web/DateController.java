package app.appointment.service.date.infrastructure.web;

import app.appointment.service.date.application.*;
import app.appointment.service.date.domain.model.DateRequest;
import app.appointment.service.date.domain.model.DateResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/date")
@AllArgsConstructor
public class DateController {

    private final CreateDate createDate;
    private final GetAllDate getAllDate;
    private final GetOneDate getOneDate;
    private final UpdateDate updateDate;
    private final DeleteDate deleteDate;

    // CREATE
    @PreAuthorize("hasAnyRole('MEDICAL', 'ADMIN')")
    @PostMapping
    public ResponseEntity<DateResponse> createDate(@RequestBody @Valid DateRequest dateRequest) {
        return ResponseEntity.of(Optional.of(createDate.execute(dateRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<DateResponse>> getAllDates() {
        return ResponseEntity.of(Optional.of(getAllDate.execute()));

    }

    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @GetMapping("/{id}")
    public ResponseEntity<DateResponse> getDateById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getOneDate.execute(id)));
    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @PutMapping("/{id}")
    public ResponseEntity<DateResponse> updateDate(@PathVariable String id, @Valid @RequestBody DateRequest medicalEntity) {
        return ResponseEntity.of(Optional.of(updateDate.execute(id,medicalEntity)));
    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDate(@PathVariable String id) {
        deleteDate.execute(id);
        return ResponseEntity.ok().build();

    }
}
