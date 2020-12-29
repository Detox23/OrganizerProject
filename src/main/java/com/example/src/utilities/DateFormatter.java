package com.example.src.utilities;

import lombok.var;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static LocalDateTime getLocalDateTimeFromString(String dateInString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var toParse = dateInString.concat(" 00:00:00");
        return LocalDateTime.parse(toParse, formatter);
    }
}
