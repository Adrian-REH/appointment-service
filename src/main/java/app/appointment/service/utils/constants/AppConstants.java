package app.appointment.service.utils.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AppConstants {

    public static final String URL_LOGIN                                = "/auth/login";


    public static final String URL_ID_PATIENT                         = "/patients/";
    public static final String URL_CREATE_PATIENT                                = "/patients";
    public static final String URL_ALL_PATIENT                                = "/patients";
    public static final String URL_UPDATE_PATIENT                                = "/patients/";
    public static final String URL_DELETE_PATIENT                                = "/patients/";
    public static final String URL_EMAIL_PATIENT                                = "/patients/email/";


    public static final String URL_ID_MEDICAL                         = "/medicals/";
    public static final String URL_CREATE_MEDICAL                                = "/medicals";
    public static final String URL_ALL_MEDICAL                                = "/medicals";
    public static final String URL_UPDATE_MEDICAL                                = "/medicals/";
    public static final String URL_DELETE_MEDICAL                                = "/medicals/";
    public static final String URL_EMAIL_MEDICAL                                = "/medicals/email/";
    
    public static final String URL_ID_DATE                         = "/date/";
    public static final String URL_CREATE_DATE                                = "/date";
    public static final String URL_ALL_DATE                                = "/date";
    public static final String URL_UPDATE_DATE                                = "/date/";
    public static final String URL_DELETE_DATE                                = "/date/";
    public static final String URL_EMAIL_DATE                                = "/date/email/";


    public static final String PASSWORD_EXIST = "1234";
    public static final String USERNAME_EXIST_PATIENT = "patient123";
    public static final String USERNAME_EXIST_MEDICAL = "medical123";
    public static final String SERVICE_CODE                                                     = "service-code";
    public static final String SERVICE_MSG                                                      = "service-message";

    public static final String ERROR                                                            = "ERROR";
}
