package app.appointment.service.patient;

import app.appointment.service.medical.domain.model.Medical;
import app.appointment.service.patient.domain.model.Patient;
import app.appointment.service.utils.InitAuthTest;
import app.appointment.service.utils.constants.AppConstants;
import app.appointment.service.utils.dto.LoginRequestTestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Rollback(true)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PatientControllerTest  extends InitAuthTest {


    @Test
    void whenGetAllPatient_responseList() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ALL_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        printResponse(mockResponse);

    }

    @Test
    void whenGetIdPatient_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ALL_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    void whenGetIdPatient_IdNotExist_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ID_PATIENT+"notExist")
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    void whenGetIdPatient_Exist_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ID_PATIENT+"65231d54fdaa3a3732cf6845")
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        printResponse(mockResponse);

    }

    @Test
    void whenGetEmailPatient_NotExist_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_EMAIL_PATIENT+"notExist")
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    void whenGetEmailPatient_Exist_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_EMAIL_PATIENT+"elias@gmail.com")
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    void whenGetEmailPatient_IdNotExist_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ID_PATIENT+"notExist")
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }


    @Test
    public void whenCreatePatient_allOk_responseOne() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .email("elias@elias.com")
                .direction("::")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
    public void whenCreatePatient_NameOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("Herrera")
                .name("")
                .dni("42121008")
                .email("elias@elias.com")
                .direction("::")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
    public void whenCreatePatient_NameLastOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("")
                .name("Elias")
                .dni("42121008")
                .email("elias@elias.com")
                .direction("::")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
    public void whenCreatePatient_DniOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("")
                .email("elias@elias.com")
                .direction("::")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
    public void whenCreatePatient_EmailOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .email("")
                .direction("::")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
    public void whenCreatePatient_EmailErrorFormat_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .email("elias.com")
                .direction("::")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
    public void whenCreatePatient_DirectionOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .email("elias@elias.com")
                .direction("")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
    public void whenCreatePatient_DirectionError_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Patient.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .email("elias@elias.com")
                .direction(":")
                .phone(null)
                .img(null)
                .tokenNot(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_PATIENT)
                        .header("Authorization", TOKEN_PATIENT_ACTUAL)
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
