package app.appointment.service.favs.infrastructure.web;

import app.appointment.service.auth.infrastructure.web.security.TokenProvider;
import app.appointment.service.favs.application.*;
import app.appointment.service.favs.domain.model.FavRequest;
import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.utils.AppUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/favs")
@AllArgsConstructor
@Slf4j
public class FavController {

    private final CreateFav createFav;
    private final GetAllFav getAllFav;
    private final GetFavById getFavById;
    private final GetFavIdMedicalAndIdPatient getFavIdMedicalAndIdPatient;
    private final UpdateFav updateFav;
    private final DeleteFav deleteFav;
    private TokenProvider jwtTokenUtil;

    // CREATE
    @PostMapping
    public ResponseEntity<FavResponse> createFav(@RequestBody @Valid FavRequest medicalRequest) {
        return ResponseEntity.of(Optional.of(createFav.execute(medicalRequest)));
    }

    // GET ALL
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<FavResponse>> getAllFavs(HttpServletRequest request) {
        String username = AppUtil.jwtGetUsername(request,jwtTokenUtil);
        return ResponseEntity.of(Optional.of(getAllFav.execute(username)));

    }




    // GET BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FavResponse> getFavById(@PathVariable String id) {
        return ResponseEntity.of(Optional.of(getFavById.execute(id)));
    }
    // GET BY ID MEDICAL AND PATIENT
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @GetMapping("patient/{idPatient}/medical/{idMedical}")
    public ResponseEntity<FavResponse> getFavById(@PathVariable String idPatient,@PathVariable String idMedical) {
        return ResponseEntity.of(Optional.of(getFavIdMedicalAndIdPatient.execute(idPatient,idMedical)));
    }

    // UPDATE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FavResponse> updateFav(@PathVariable String id, @Valid @RequestBody FavRequest medicalEntity) {
        return ResponseEntity.of(Optional.of(updateFav.execute(id,medicalEntity)));
    }

    // DELETE BY ID
    @PreAuthorize("hasAnyRole('MEDICAL','PATIENT', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<FavResponse> deleteFav(@PathVariable String id) {
        deleteFav.execute(id);

        return ResponseEntity.ok().build();



    }
}
