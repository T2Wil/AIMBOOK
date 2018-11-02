package distributorServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.Distributor;
import utils.Utils;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*A response that tells the browser to allow code from any origin to access a resource*/
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		JSONObject json = new JSONObject();
		PrintWriter writer = response.getWriter();
		Map <String,String> params ;		
		Utils utils = new Utils();
		params = utils.parseRequest(request);

		String firstName = params.get("fname_");
		String lastName = params.get("lname");
		String phoneNumber = params.get("tel");
		String  registrationNumber= params.get("code");
		String uplineregistrationNumber = params.get("uplineCode");
		String uplineSide = params.get("uplineSide");
		String dbName = params.get("db");
		String password = params.get("password");
				
		Distributor distributor = new Distributor ();
		
		if(firstName != null && lastName  != null &&  phoneNumber != null && registrationNumber != null 
				&& uplineregistrationNumber != null && uplineSide != null&& dbName != null && 
				password != null) {
			distributor.setFirstName(firstName);
			distributor.setLastName(lastName);
			distributor.setPhoneNumber(phoneNumber);
			distributor.setRegistrationNumber(uplineregistrationNumber);
			distributor.setUplineregistrationNumber(uplineregistrationNumber);
			distributor.setUplineSide(uplineSide);
			distributor.setDbName(dbName);
			distributor.setPassword(password);
					
		}
		
		boolean feedback = distributor.registerDistributor(distributor);
		
		
		
		try {
			json.put("feedback", feedback);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}	
		finally {
			writer.println(json);	

		}

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}
