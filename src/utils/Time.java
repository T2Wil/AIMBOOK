package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
public String writeTime() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	   LocalDateTime now = LocalDateTime.now();
	   return dtf.format(now).toString();
}
}
