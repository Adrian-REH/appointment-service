package app.appointment.service.date.application;

import app.appointment.service.date.application.utils.UtilsDate;
import app.appointment.service.date.domain.model.DateRequest;
import app.appointment.service.date.domain.model.DateResponse;
import app.appointment.service.date.domain.port.DateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateDate {
    private final DateRepository dateRepository;

    public DateResponse execute(String id, DateRequest dateRequest) {
        String[] days = {
                dateRequest.getMonday(),
                dateRequest.getTuesday(),
                dateRequest.getWednesday(),
                dateRequest.getThursday(),
                dateRequest.getFriday(),
                dateRequest.getSunday()
        };
        for (String day : days) {
            UtilsDate.verifyRequestHoras(day);
        }

        dateRepository.findById(id);

        return dateRepository.updateById(id, dateRequest);
    }
}
