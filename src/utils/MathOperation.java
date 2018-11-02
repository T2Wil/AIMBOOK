package utils;

import java.security.SecureRandom;
import org.json.JSONException;
import org.json.JSONObject;

public class MathOperation {
	
/*generates secure random number
 * and save it.
 * */
	public int generateSecureNumber(){
		
		JSONObject usedNumbers;
		SecureRandom securedNumber;
		int genNumber;
		String savedNumber = null;
		FileOperation fRead;
		int loopingFlag = 1;
		
		fRead = new FileOperation();
		usedNumbers = fRead.readFile("securedNumbers.txt");
		
		securedNumber = new SecureRandom();
		genNumber = securedNumber.nextInt(99999999);
		
		while (loopingFlag == 1){
		for(int key = usedNumbers.length(); key  > 0 ; key--){ 
			try {
				savedNumber = usedNumbers.getString(Integer.toString(key));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if(savedNumber.equals(Integer.toString(genNumber))){
				genNumber = securedNumber.nextInt(99999999);   
				break;
			}
			if( key == 1)
				loopingFlag = 0;
		}
 
		}
		return genNumber;
		
	}

}
