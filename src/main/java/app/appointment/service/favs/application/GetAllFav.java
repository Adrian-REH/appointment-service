package app.appointment.service.favs.application;

import app.appointment.service.favs.domain.model.FavResponse;
import app.appointment.service.favs.domain.port.FavRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GetAllFav {
    private final FavRepository favRepository;
    public List<FavResponse> execute() {
        return favRepository.getAllFavs();
    }
}
