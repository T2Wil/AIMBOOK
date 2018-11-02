package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class FileOperation {
	/*Writing/append to a file name
	 * pass fileName with extension
	 * */	
	public boolean writeToFile(String fileName,String data){
		boolean result = true;
		File file;
		FileWriter fileWriter = null;
		BufferedWriter bufferedwriter = null;

				try {
					file = new File ("/usr/local/tomcat/webapps/aimbook/WEB-INF/lib/"+fileName);
					if(!file.exists())
					file.createNewFile();
					fileWriter = new FileWriter(file,true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					file = new File("/home/t/eclipse-workspace/aimbook/WebContent/WEB-INF/lib/"+fileName);
					
				
						try {
						    if(!file.exists())
							file.createNewFile();
							fileWriter = new FileWriter(file,true);

						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			try {
				
			bufferedwriter= new BufferedWriter(fileWriter);
			bufferedwriter.write(data);
			bufferedwriter.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(bufferedwriter != null)
					bufferedwriter.close();
				if(fileWriter != null)
					fileWriter.close();
			} catch (IOException e) {
				result = false;
				e.printStackTrace();
			}
		
		}
		return result;
		}
	/*Read a file
	 * fileName must be passed with its extension
	 */
	public JSONObject readFile(String fileName){
		JSONObject json = null;
		File file;
		FileReader fReader = null ;
		BufferedReader bReader = null;
		int counter = 0;
		
		try {
			file = new File ("/usr/local/tomcat/webapps/aimbook/WEB-INF/lib/"+fileName);
			if(!file.exists())
			file.createNewFile();
			fReader = new FileReader(file);
		} catch (Exception e1) {
			file = new File("/home/t/eclipse-workspace/aimbook/WebContent/WEB-INF/lib/" + fileName);
			
		
				try {
				    if(!file.exists())
					file.createNewFile();
				    fReader = new FileReader(file);

				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		try {
			bReader = new BufferedReader(fReader);
			json = new JSONObject();
			while(bReader.ready()){
				json.put(Integer.toString(++counter), bReader.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
				try {
					if(fReader != null)
						fReader.close();
					if(bReader != null)
						bReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return json;
	}
}
