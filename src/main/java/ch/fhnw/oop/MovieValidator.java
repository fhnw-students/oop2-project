package ch.fhnw.oop;

public class MovieValidator {

    public static boolean isValidYear(String value) {
        return value == null || value.matches("\\d+") && value.length() == 4;
    }

    public static boolean isRequired(String value){
       return value != null && value.length() > 0;
    }

    public static boolean isNumber(String value){
        return value == null || value.matches("\\d+");
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
        return value == null || value.matches("-") || value.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}");
    }

}
