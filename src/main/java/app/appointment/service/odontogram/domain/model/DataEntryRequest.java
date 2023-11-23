package app.appointment.service.odontogram.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataEntryRequest {
    @NotBlank
    private String number;
    @NotBlank
    private String imgTop;
    @NotBlank
    private String imgBot;

}
