package app.appointment.service.date;

import app.appointment.service.date.domain.model.Date;
import app.appointment.service.medical.domain.model.Medical;
import app.appointment.service.utils.InitAuthTest;
import app.appointment.service.utils.constants.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DateControllerTest extends InitAuthTest {

    @Test
    public void createDate_okRequest_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_mondayRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 08:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_tuesdayRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 08:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_wednesdayRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 09:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_thursdayRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 09:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_fridayRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 09:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_saturdayRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 09:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_sundayRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("6519c8aa7127f27f844bdc28")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 09:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void createDate_idMedicalRequestFail_DateResponse() throws Exception {
        String payload = null;

        ObjectMapper mapper = new ObjectMapper();
        payload = mapper.writeValueAsString(Date.builder()
                .idMedical("other")
                .monday("09:00 de 15:00")
                .tuesday("09:00 de 15:00")
                .wednesday("09:00 de 15:00")
                .thursday("09:00 de 15:00")
                .friday("09:00 de 15:00")
                .saturday("09:00 de 15:00")
                .sunday("09:00 de 15:00")
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_ALL_DATE)
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }




}
