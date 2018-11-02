package tests;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.FileOperation;
import utils.Time;

public class Main {

	public static void main(String[] args) {
		FileOperation currentDate,currentLog ;
		currentDate = new FileOperation();
		currentLog = new FileOperation();
		 Time currentTime = new Time();
		 
		 System.out.println(currentTime.writeTime());
		 
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
//		  LocalDateTime now = LocalDateTime.now();
//		System.out.println(currentDate.writeToFile("aimbookLogs.txt",dtf.format(now)));
//		//System.out.println(currentLog.writeToFile("aimbookLogs.txt","aimlogsssss"));
		  
	}

}
