package app.appointment.service.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Jonathan Giovanni Hernandez
 * @company Finansoportes Consulting
 * @created 13/08/2020
 */
public class AppUtil {

    public static Integer safetyParseInteger(String possibleInteger){
        if(NumberUtils.isDigits(possibleInteger)){
            return Integer.parseInt(possibleInteger);
        }
        return -1;
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date addMonths(Date date, int months)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months); //minus number would decrement the days
        return cal.getTime();
    }

    public static String dateWithoutHoursLongFormat(Date date){

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }

    public static Date dateWithoutHours(Date date){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date addYears(Date date, int years)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years); //minus number would decrement the days
        return cal.getTime();
    }

    public static int numberOfOccurences(String findWord, String sentence) {
        int length = sentence.length();
        int lengthWithoutFindWord = sentence.replace(findWord, "").length();
        return (length - lengthWithoutFindWord)/findWord.length();
    }

    public static String encodeURIComponent(String s)
    {
        String result = null;

        try
        {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e)
        {
            result = s;
        }

        return result;
    }


    public static String date2StringWithoutHoursPaylandFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date string2DateWithoutHoursPaylandFormat(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public static String date2String(Date date){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }


    public static String date2StringOnlyYear(Date date){
        return new SimpleDateFormat("yyyy").format(date);
    }

    public static String date2StringWithoutHours(Date date){
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static String currentDate(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public static String generate4DigitCode(){
        return String.format("%04d", new Random().nextInt(10000));
    }

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Get Final cause of exception
     * @param t
     * @return Throwable
     */
    public static String getFinalCause(Throwable t){
        if(t.getCause()!=null){
            return getFinalCause(t.getCause());
        }else{
            return t.toString().replaceAll("(\\r|\\n|\\t)", " ");
        }
    }

    /**
     * Get Class name by depth level
     * @param level
     * @return Class Name
     */
    public static String getClassName(int level) {
        return Thread.currentThread().getStackTrace()[level].getClassName();
    }

    /**
     * Get Class name default depth level 2
     * @return Class Name
     */
    public static String getClassName() {
        return getClassName(2);
    }

    /**
     * Get Method name by depth level
     * @param level
     * @return Method Name
     */
    public static String getMethodName(int level) {
        return Thread.currentThread().getStackTrace()[level].getMethodName();
    }

    /**
     * Get Method name default depth level 2
     * @return Method Name
     */
    public static String getMethodName() {
        return getMethodName(2);
    }

    public static String getMethodWithClass(){
        String clazz = getClassName(3);
        clazz =  clazz.substring(clazz.lastIndexOf(".")+1);
        return clazz+"."+getMethodName(3);
    }

    public static String sha256Encode(String content){
        return DigestUtils.sha256Hex(content);
    }

    public static String base64Encode(String content){
        return Base64.getEncoder().encodeToString(content.getBytes());
    }
    public static String generatedCode(){

        int minDigits = 6;
        int maxDigits = 9;

        Random random = new Random();
        int numDigits = random.nextInt(maxDigits - minDigits + 1) + minDigits;

        StringBuilder codigoAleatorio = new StringBuilder();
        for (int i = 0; i < numDigits; i++) {
            int digito = random.nextInt(10);
            codigoAleatorio.append(digito);
        }
        return String.valueOf(codigoAleatorio);
    }


}
