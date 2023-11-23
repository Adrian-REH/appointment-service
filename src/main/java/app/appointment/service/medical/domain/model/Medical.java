package app.appointment.service.medical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Medical {

    private String id;
    private String nameLast;
    private String name;
    private String dni;
    private String phone;
    private String email;
    private String direction;
    private String tuition;
    private String profession;
    private String img;
    private String tokenNot;
    private String hourOn;
}
