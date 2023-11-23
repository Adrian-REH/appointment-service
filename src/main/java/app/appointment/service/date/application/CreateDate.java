package app.appointment.service.date.application;

import app.appointment.service.date.application.utils.UtilsDate;
import app.appointment.service.date.domain.model.DateRequest;
import app.appointment.service.date.domain.model.DateResponse;
import app.appointment.service.date.domain.port.DateRepository;
import app.appointment.service.medical.domain.port.MedicalRepository;
import app.appointment.service.patient.domain.model.PatientRequest;
import app.appointment.service.patient.domain.model.PatientResponse;
import app.appointment.service.utils.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.apache.coyote.Constants;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateDate {

    private final DateRepository dateRepository;
    private final MedicalRepository medicalRepository;
    public DateResponse execute(DateRequest dateRequest) {
        String[] days = {
                dateRequest.getMonday(),
                dateRequest.getTuesday(),
                dateRequest.getWednesday(),
                dateRequest.getThursday(),
                dateRequest.getFriday(),
                dateRequest.getSaturday(),
                dateRequest.getSunday()
        };
        for (String day : days) {
            UtilsDate.verifyRequestHoras(day);
        }

        medicalRepository.findById(dateRequest.getIdMedical());

        return dateRepository.save(dateRequest);
    }
}
