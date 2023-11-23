package app.appointment.service.auth;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Rollback(true)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @Test
    public void whenLogin_UserAndPasswordIsBlank_Exception() throws Exception {
        login("","",status().isBadRequest());

    }

    @Test
    public void whenLogin_UserIsBlank_Exception() throws Exception {
        login("",AppConstants.PASSWORD_EXIST,status().isBadRequest());

    }

    @Test
    public void whenLogin_PasswordIsBlank_Exception() throws Exception {
        login(AppConstants.USERNAME_EXIST_MEDICAL,"",status().isUnauthorized());

    }

    @Test
    public void whenLogin_RequestMedicalOk_ResponseJwt() throws Exception {
        login(AppConstants.USERNAME_EXIST_MEDICAL,AppConstants.PASSWORD_EXIST, status().isOk());

    }

    @Test
    public void whenLogin_RequestPatientOk_ResponseJwt() throws Exception {
        login(AppConstants.USERNAME_EXIST_PATIENT,AppConstants.PASSWORD_EXIST, status().isOk());

    }


    private void login(final String user, final String password, ResultMatcher matcher) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(
                LoginRequestTestDto.builder()
                        .username(user)
                        .password(password)
                        .build());

        MvcResult mockResponse = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.URL_LOGIN)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(matcher)
                .andReturn();


    }

}
