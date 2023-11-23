package app.appointment.service.medical;

import app.appointment.service.medical.domain.model.Medical;
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
public class MedicalControllerTest extends InitAuthTest {


    @Test
    public void whenGetAllMedical_responseList() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ALL_MEDICAL)
                .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        printResponse(mockResponse);

    }

    @Test
    public void whenGetIdMedical_Exist_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ID_MEDICAL+"6519c8aa7127f27f844bdc28")
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        printResponse(mockResponse);

    }
    @Test
    public void whenGetIdMedical_IdNotExist_responseOne() throws Exception {
        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.URL_ID_MEDICAL+"notExist")
                        .header("Authorization", TOKEN_MEDICAL_ACTUAL)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        printResponse(mockResponse);

    }

    @Test
    public void whenCreateMedical_allOk_responseOne() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .phone("1125434828")
                .email("elias@elias.com")
                .direction("::")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_NameOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("")
                .dni("42121008")
                .phone("1125434828")
                .email("elias@elias.com")
                .direction("::")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_NameLastOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("")
                .name("Elias")
                .dni("42121008")
                .phone("1125434828")
                .email("elias@elias.com")
                .direction("::")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_DniOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("")
                .phone("1125434828")
                .email("elias@elias.com")
                .direction("::")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_PhoneOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .phone("")
                .email("elias@elias.com")
                .direction("::")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_EmailOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .phone("1125434828")
                .email("")
                .direction("::")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_EmailErrorFormat_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .phone("1125434828")
                .email("elias.com")
                .direction("::")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_DirectionOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .phone("1125434828")
                .email("elias@elias.com")
                .direction("")
                .tuition("451234r:f")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_DirectionError_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .phone("1125434828")
                .email("elias@elias.com")
                .direction(":")
                .tuition("")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
    public void whenCreateMedical_TuitionOrBlankNull_Exception() throws Exception {
        String payload = null;
        ObjectMapper mapper = new ObjectMapper();

        payload = mapper.writeValueAsString(Medical.builder()
                .nameLast("Herrera")
                .name("Elias")
                .dni("42121008")
                .phone("1125434828")
                .email("elias@elias.com")
                .direction("::")
                .tuition("")
                .profession(null)
                .img(null)
                .tokenNot(null)
                .hourOn(null)
                .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_CREATE_MEDICAL)
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
