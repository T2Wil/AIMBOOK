package utils;

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
}
