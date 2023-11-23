package app.appointment.service.utils;


import app.appointment.service.auth.domain.model.LoginRequest;
import app.appointment.service.utils.constants.AppConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public abstract class InitAuthTest {
    private static final Logger log = LoggerFactory.getLogger(InitAuthTest.class);
    public String TOKEN_MEDICAL_ACTUAL;
    public String TOKEN_PATIENT_ACTUAL;

    @Autowired
    public MockMvc mockMvc;

    @PostConstruct
    public void init() throws  Exception{
        List<LoginRequest> listLogin = new ArrayList<>();

        listLogin.add(new LoginRequest(AppConstants.USERNAME_EXIST_PATIENT, AppConstants.PASSWORD_EXIST));
        listLogin.add(new LoginRequest(AppConstants.USERNAME_EXIST_MEDICAL, AppConstants.PASSWORD_EXIST));

        for (int i=0;i<listLogin.size();i++){
            ObjectMapper mapper = new ObjectMapper();
            String payload = null;

            payload = mapper.writeValueAsString(listLogin.get(i));

            final MvcResult result;

            result = mockMvc.perform(post(AppConstants.URL_LOGIN)
                            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                            .content(payload)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andReturn();


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseJson = null;

            responseJson = objectMapper.readTree(result.getResponse().getContentAsByteArray());

            switch (i) {
                case 0 -> TOKEN_PATIENT_ACTUAL = "Bearer " + responseJson.get("token").asText();
                case 1 -> TOKEN_MEDICAL_ACTUAL = "Bearer " + responseJson.get("token").asText();
                default -> {
                }
            }
        }

    }
    public static void printResponse(MvcResult result) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> headers = new HashMap<>();
        result.getResponse().getHeaderNames().forEach(resHeader->headers.put(resHeader, result.getResponse().getHeader(resHeader)));

        log.info("header  : \n{}", mapper.writeValueAsString(headers));
        log.info("body  : \n{}", result.getResponse().getContentAsString());
    }
}
