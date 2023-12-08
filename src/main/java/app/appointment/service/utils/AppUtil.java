package app.appointment.service.utils;

import app.appointment.service.auth.infrastructure.web.security.TokenProvider;
import app.appointment.service.utils.dto.EmailNotificationDto;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@AllArgsConstructor
public class AppUtil {

    public static Integer safetyParseInteger(String possibleInteger) {
        if (NumberUtils.isDigits(possibleInteger)) {
            return Integer.parseInt(possibleInteger);
        }
        return -1;
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months); //minus number would decrement the days
        return cal.getTime();
    }

    public static String dateWithoutHoursLongFormat(Date date) {

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }

    public static Date dateWithoutHours(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years); //minus number would decrement the days
        return cal.getTime();
    }

    public static int numberOfOccurences(String findWord, String sentence) {
        int length = sentence.length();
        int lengthWithoutFindWord = sentence.replace(findWord, "").length();
        return (length - lengthWithoutFindWord) / findWord.length();
    }

    public static String encodeURIComponent(String s) {
        String result = null;

        try {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }


    public static String date2StringWithoutHoursPaylandFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date string2DateWithoutHoursPaylandFormat(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public static String date2String(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }


    public static String date2StringOnlyYear(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

    public static String date2StringWithoutHours(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static String currentDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public static String generate4DigitCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Get Final cause of exception
     *
     * @param t
     * @return Throwable
     */
    public static String getFinalCause(Throwable t) {
        if (t.getCause() != null) {
            return getFinalCause(t.getCause());
        } else {
            return t.toString().replaceAll("(\\r|\\n|\\t)", " ");
        }
    }

    /**
     * Get Class name by depth level
     *
     * @param level
     * @return Class Name
     */
    public static String getClassName(int level) {
        return Thread.currentThread().getStackTrace()[level].getClassName();
    }

    /**
     * Get Class name default depth level 2
     *
     * @return Class Name
     */
    public static String getClassName() {
        return getClassName(2);
    }

    /**
     * Get Method name by depth level
     *
     * @param level
     * @return Method Name
     */
    public static String getMethodName(int level) {
        return Thread.currentThread().getStackTrace()[level].getMethodName();
    }

    /**
     * Get Method name default depth level 2
     *
     * @return Method Name
     */
    public static String getMethodName() {
        return getMethodName(2);
    }

    public static String getMethodWithClass() {
        String clazz = getClassName(3);
        clazz = clazz.substring(clazz.lastIndexOf(".") + 1);
        return clazz + "." + getMethodName(3);
    }

    public static String sha256Encode(String content) {
        return DigestUtils.sha256Hex(content);
    }

    public static String base64Encode(String content) {
        return Base64.getEncoder().encodeToString(content.getBytes());
    }

    public static String generatedCode() {

        int minDigits = 6;

        Random random = new Random();

        StringBuilder codigoAleatorio = new StringBuilder();
        for (int i = 0; i < minDigits; i++) {
            int digito = random.nextInt(10);
            codigoAleatorio.append(digito);
        }
        return String.valueOf(codigoAleatorio);
    }

    public static String generateHTMLVerifyEmailCode(EmailNotificationDto notificationDto) {

        var body = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><title>Codigo de Verificacion</title><style>body{font-family:Arial,sans-serif;background-color:#f4f4f4;margin:0;padding:0;text-align:center}.container{width:100%;max-width:600px;margin:0 auto;background-color:#fff;border-radius:8px;box-shadow:0 4px 8px rgba(0,0,0,.1);text-align:center}h1{color:#333}p{color:#555}.verification-code{font-size:36px;font-weight:700;color:#007bff;margin-top:20px;margin-bottom:30px}.note{color:#888}footer{margin-top:30px;color:#888}</style></head><body><center style=\"width:100%,height:100%;table-layout:fixed;background-color:#f4f4f4\"><table class=\"container\" align=\"center\" style=\"margin:auto auto\"><tr><td><h1>Codigo de Verificacion</h1><p class=\"note\">Hola! {{name}}, Utiliza el siguiente codigo para verificar tu correo electronico y cambiar tu clave.</p><div class=\"verification-code\">{{code}}</div><p class=\"note\">Este Codigo expirara en 30 minutos ({{expiryCode}}).</p><p>Este correo electronico fue enviado automaticamente. Por favor, no respondas a este correo.</p></td></tr></table></center></body></html>";


        var name = notificationDto.getPatient() + notificationDto.getMedical();
        var code = notificationDto.getTwoFactorCode();

        // Definir un formato personalizado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String expiryCode = notificationDto.getTwoFactorExpiryCode().format(formatter);
        return body.replace("{{name}}", name).replace("{{code}}", code).replace("{{expiryCode}}", expiryCode);
    }

    public static String generateHTMLVerifyEmail(EmailNotificationDto notificationDto) {
        return "";
    }

    public static String generateHTMLResetPassword(EmailNotificationDto notificationDto) {
        var body = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><title>Se cambio la clave con exito</title><style>body{font-family:Arial,sans-serif;background-color:#f4f4f4;margin:0;padding:0;text-align:center}.container{width:100%;max-width:600px;margin:0 auto;background-color:#fff;border-radius:8px;box-shadow:0 4px 8px rgba(0,0,0,.1);text-align:center}h1{color:#333}p{color:#555}.verification-code{font-size:36px;font-weight:700;color:#007bff;margin-top:20px;margin-bottom:30px}.note{color:#888}footer{margin-top:30px;color:#888}</style></head><body><center style=\"width:100%,height:100%;table-layout:fixed;background-color:#f4f4f4\"><table class=\"container\" align=\"center\" style=\"margin:auto auto\"><tr><td><h1>Cambio de clave</h1><p class=\"note\">Hola! {{name}}, cambiaste tu clave de la cuenta.</p><div class=\"verification-code\">{{username}}</div><p>Este correo electronico fue enviado automaticamente. Por favor, no respondas a este correo.</p></td></tr></table></center></body></html>";


        var name = notificationDto.getPatient() + notificationDto.getMedical();
        var username = notificationDto.getUsername();

        // Definir un formato personalizado
        return body.replace("{{name}}", name).replace("{{username}}", username);

    }

    public static String generateHTMLNewAppointment(EmailNotificationDto notificationDto) {
        return "";
    }

    public static String generateHTMLInicioSession(EmailNotificationDto notificationDto) {
        return "";
    }

    public static String generateHTMLNewAccount(EmailNotificationDto notificationDto) {
        String body = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><title>Bienvenido a nuestro servicio</title><style>body{font-family:Arial,sans-serif;background-color:#f4f4f4;margin:0;padding:0;text-align:center}.container{width:100%;max-width:600px;margin:0 auto;background-color:#fff;border-radius:8px;box-shadow:0 4px 8px rgba(0,0,0,.1);text-align:center}h1{color:#333}p{color:#555}.verification-code{font-size:36px;font-weight:700;color:#007bff;margin-top:20px;margin-bottom:30px}.note{color:#888}footer{margin-top:30px;color:#888}</style></head><body><center style=\"width:100%,height:100%;table-layout:fixed;background-color:#f4f4f4\"><table class=\"container\" align=\"center\" style=\"margin:auto auto\"><tr><td><h1>Bienvenido a nuestro servicio</h1><p class=\"note\">Gracias por unirte a nosotros, {{name}}!</p><p>Apreciamos tu eleccion de ser parte de nuestro servicio. Tu nombre de usuario es:</p><p><span class=\"verification-code\">{{username}}</span></p><p>Te animamos a completar tus datos personales para aprovechar al maximo nuestro servicio y poder sacar turnos facilmente.</p><p>Por favor,<a href=\"http://localhost:8080/auth/verify-email/{{username}}\" target=\"_blank\">verifica tu correo electronico</a> para garantizar la seguridad de tu cuenta.</p><p>Este correo electronico fue enviado automaticamente. Por favor, no respondas a este correo.</p></td></tr></table></center></body></html>";

        var name = notificationDto.getPatient() + notificationDto.getMedical();
        var username = notificationDto.getUsername();

        // Definir un formato personalizado
        return body.replace("{{name}}", name).replace("{{username}}", username);
    }
    public static String jwtGetUsername(HttpServletRequest request,TokenProvider jwtTokenUtil) {

        return jwtTokenUtil.getUsernameFromToken(jwtToken(request));
    }
    public static String jwtGetRole(HttpServletRequest request,TokenProvider jwtTokenUtil) {

        return jwtTokenUtil.getAllClaimsFromToken(jwtToken(request)).get("role").toString().replaceFirst("ROLE_","");
    }

    private static String jwtToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String authToken = null;
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.replace("Bearer ","");
            //  isMedical = jwtTokenUtil.getAllClaimsFromToken(authToken).get("isMedical").equals("true");

        }

        return authToken;
    }
}
