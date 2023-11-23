package app.appointment.service.config;


import app.appointment.service.utils.constants.ServiceConstants;
import app.appointment.service.utils.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

@Slf4j
@Configuration
public class FeignClientErrorDecoder implements ErrorDecoder {
    private final ObjectMapper mapper       = new ObjectMapper();
    @Override
    public Exception decode(String methodKey, Response response) {

        String clazzRequestedName   = methodKey.substring(0,methodKey.indexOf("#"));
        String methodRequestedName  = methodKey.substring(methodKey.indexOf("#")+1,methodKey.indexOf("("));
        String body                 = extractContent(response);

        log.info("ERROR CONSUMING API CLASS: "+clazzRequestedName+" , METHOD: "+methodRequestedName+" RESPONSE "+response.toString()+" BODY: "+body);

        //respuesta generica a error de credenciales
        if(response.status()==401 || response.status()==403){
            return new ServiceException(ServiceConstants.SERVICE_CODE_800, HttpStatus.INTERNAL_SERVER_ERROR,clazzRequestedName);
        }

        //escenarios de error para cada uno de los clientes

     /*   if(clazzRequestedName.contains(PaylandClient.class.getSimpleName())){
            return processPaylandError(methodKey,clazzRequestedName,methodRequestedName,response,body);
        }*/

        return new ServiceException(ServiceConstants.SERVICE_CODE_801, HttpStatus.BAD_REQUEST,response.reason());
    }



    private String extractContent(Response response) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try{
            StreamUtils.copy(response.body().asInputStream(), output);
        } catch (FileNotFoundException ex) {
            //log.error("ERROR DECODING BODY");
        } catch (Exception ex) {
            //log.error("ERROR DECODING BODY");
        }
        return output.toString();
    }


    public static boolean isJson(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
