package app.appointment.service.medical.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalResponse {
    private String id;
    private String nameLast;
    private String username;
    private String dni;
    private String phone;
    private String email;
    private String direction;
    private String tuition;
    private String profession;
    private String img;
    private String tokenNot;
    private String hourOn;
    private List<Schedule> listSchedule;
    private List<Specialty> listSpecialty;

}
