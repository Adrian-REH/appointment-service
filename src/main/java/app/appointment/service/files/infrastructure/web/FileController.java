package app.appointment.service.files.infrastructure.web;

import app.appointment.service.auth.infrastructure.web.security.TokenProvider;
import app.appointment.service.files.application.*;
import app.appointment.service.files.domain.model.FileRequest;
import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.utils.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequestMapping(value ="/files")
public class FileController {
    private final CreateFile createFile;
    private final GetAllFile getAllFile;
    private final GetOneFile getOneFile;
    private final GetFileIdMedical getFileIdMedical;
    private final GetFileIdPatient getFileIdPatient;
    private final GetFileIdPatientAndIdMedical getFileIdPatientAndIdMedical;
    private final UpdateFile updateFile;
    private final DeleteFile deleteFile;
    private final TokenProvider jwtTokenUtil;

    // CREATE
    @PostMapping
    public ResponseEntity<FileResponse> createFile(@RequestBody @Valid FileRequest medicalRequest) {
        return ResponseEntity.of(Optional.of(createFile.execute(medicalRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<FileResponse>> getAllFiles(HttpServletRequest request) {
        final String username = AppUtil.jwtGetUsername(request, jwtTokenUtil);
        final String role = AppUtil.jwtGetRole(request, jwtTokenUtil);

        return ResponseEntity.of(Optional.of(getAllFile.execute(username,role)));

    }

    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<FileResponse>> getFilePatient(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getFileIdPatient.execute(id)));
    }
    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/patient/{idPatient}/medical/{idMedical}")
    public ResponseEntity<FileResponse> getFilePatient(@PathVariable String idPatient, @PathVariable String idMedical) {
        return ResponseEntity.of(Optional.of(getFileIdPatientAndIdMedical.execute(idPatient, idMedical)));
    }
    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/medical/{id}")
    public ResponseEntity<List<FileResponse>> getFileMedical(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getFileIdMedical.execute(id)));
    }
    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FileResponse> getFileById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getOneFile.execute(id)));
    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @PutMapping("/{id}")
    public ResponseEntity<FileResponse> updateFile(@PathVariable String id, @Valid @RequestBody FileRequest medicalEntity) {
        return ResponseEntity.of(Optional.of(updateFile.execute(id,medicalEntity)));
    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteFile(@PathVariable String id) {
        deleteFile.execute(id);
        return ResponseEntity.ok().build();

    }
}
