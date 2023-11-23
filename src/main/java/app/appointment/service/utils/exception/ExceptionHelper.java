package app.appointment.service.utils.exception;


import app.appointment.service.utils.AppUtil;
import app.appointment.service.utils.constants.AppConstants;
import app.appointment.service.utils.constants.ServiceConstants;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan Giovanni Hernandez
 * @company Finansoportes Consulting
 * @created 01/09/2020
 */
@Component
public class ExceptionHelper {

    private Environment env;

    ExceptionHelper(Environment env){
        this.env = env;
    }

    /**
     * <strong>Obtiene las propiedades del archivo de configuracion para codigos de error</strong>
     * @param serviceCode
     * @return String
     */
    public String getServiceMsg(int serviceCode){
        return env.getProperty(ServiceConstants.PREFIX+serviceCode);
    }
    /**
     * <strong>Estable los codigos de error para las excepciones manejadas</strong>
     * @param headers
     * @param e
     * @return HttpHeaders
     */
    public HttpHeaders serviceException(HttpHeaders headers, ServiceException e){
        headers.add(AppConstants.SERVICE_CODE,String.valueOf(e.getServiceCode()));
        String msg = getServiceMsg( e.getServiceCode() );
        if(e.getElements()!=null && e.getElements().length>0){
            msg = String.format(msg, (Object[]) e.getElements());
        }
        headers.add(AppConstants.SERVICE_MSG,msg);
        return headers;
    }

    /**
     * <strong>Establece los codigos de error para excepciones no manejada</strong>
     * @param headers
     * @param e
     * @return HttpHeaders
     */
    public HttpHeaders unhandledException(HttpHeaders headers, Exception e){
        headers.add(AppConstants.SERVICE_CODE,String.valueOf(ServiceConstants.SERVICE_CODE_601));
        headers.add(AppConstants.SERVICE_MSG,String.format( getServiceMsg( ServiceConstants.SERVICE_CODE_601 ), AppUtil.getFinalCause(e) ));
        return headers;
    }

    /**
     * <strong>Establece un codigo de servicio personalizado a lo Headers</strong>
     * @param headers
     * @param serviceCode
     * @return HttpHeaders
     */
    public HttpHeaders customServiceCode(HttpHeaders headers,int serviceCode,String... values){
        headers.add(AppConstants.SERVICE_CODE,String.valueOf(serviceCode));
        String msg = getServiceMsg(serviceCode);
        if(values!=null && values.length>0){
            msg = String.format(msg, (Object[]) values);
        }
        headers.add(AppConstants.SERVICE_MSG,msg);
        return headers;
    }

}
