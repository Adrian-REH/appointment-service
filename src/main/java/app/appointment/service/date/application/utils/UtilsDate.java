package app.appointment.service.date.application.utils;

import app.appointment.service.utils.exception.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsDate {

    public static void verifyRequestHoras(String horatioDay){

        // Divide el horario en dos partes (izquierda y derecha)
        String[] partes = horatioDay.split(" de ");

        if (partes.length != 2) {
            System.out.println("El formato del horario no es valido.");
        }
        else {
            String horaIzquierda = partes[0];
            String horaDerecha = partes[1];
            if (verificarHoras(horaIzquierda, horaDerecha)) {
                throw new ServiceException("El horario no es válido");
            }
        }
    }

    private static boolean verificarHoras(String horaIzquierda, String horaDerecha) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
            Date fechaIzquierda = formato.parse(horaIzquierda);
            Date fechaDerecha = formato.parse(horaDerecha);

            return fechaDerecha.before(fechaIzquierda) || fechaIzquierda.equals(fechaDerecha);
        } catch (ParseException e) {
            // Error al analizar las horas
            return false;
        }
    }
}
