package app.appointment.service.medical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private String title;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
    private Date createdAt;

}
