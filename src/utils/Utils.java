package utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public Map<String, String> parseRequest(HttpServletRequest request){
		Map<String, String> newEntries = new LinkedHashMap<>();
		
		//keyset returns the keys that HashMap contains.
		Set <String> set  = request.getParameterMap().keySet();
		ArrayList <String> keys = new ArrayList<>();
		keys.addAll(set);
		for (String key : keys){
			newEntries.put(key,request.getParameterMap().get(keys.get(keys.indexOf(key)))[0]);
		}
		
		return newEntries;
		
	}
	
	public boolean writeException(String fileName,Exception e){
		FileOperation newfile;
		Time currentTime;
		newfile = new FileOperation();
	    currentTime = new Time();
	    boolean result = false;
		if(newfile.writeToFile(fileName, currentTime.writeTime()))
	    	if(newfile.writeToFile(fileName, e.getMessage()))
	    		result = true;
		return result;
	}
	
//	public boolean writeException(String fileName,Exception e) {
//		StringWriter writer = new StringWriter();
//		PrintWriter printWriter = new PrintWriter( writer );
//		e.printStackTrace( printWriter );
//		printWriter.flush();
//
//		String stackTrace = writer.toString();
//		
//		FileOperation newfile;
//		Time currentTime;
//		newfile = new FileOperation();
//	    currentTime = new Time();
//	    boolean result = false;
//		if(newfile.writeToFile(fileName, currentTime.writeTime()))
//	    	if(newfile.writeToFile(fileName, stackTrace))
//	    		result = true;
//		return result;
//	}
}
