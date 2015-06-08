package ch.fhnw.oop;

import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by davidheimgartner on 08.06.15.
 */
public class MovieValidator {

    public static boolean isValidYear(String value){
        return value.matches("\\d+") && value.length() == 4;
    }

    public static boolean isRequired(String value){
       return value.length() > 0;
    }

    public static boolean isNumber(String value){
        return value.matches("\\d+");
    }

    public static boolean isFlag(String value) {
        String[] strings = value.split("/");
        boolean ok = true;

        for (String string : strings) {
            if (!string.matches("[a-zA-Z]{2}")) {
                ok = false;
            }
        }
        return ok;
    }
    public static boolean isDate(String value){
        return value.matches("-") || value.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}");
    }

}
