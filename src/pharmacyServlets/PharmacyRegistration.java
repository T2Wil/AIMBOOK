package pharmacyServlets;

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

import model.Pharmacy;
import utils.Utils;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/PharmacyRegistration")
public class PharmacyRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PharmacyRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*A response that tells the browser to allow code from any origin to access a resource*/
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Registration started");
		
		boolean result;
		String firstName,lastName,password;
		JSONObject json = new JSONObject();
		PrintWriter writer = response.getWriter();
		Pharmacy pharmacy = new Pharmacy();
		Utils exception;

		Map <String,String> params ;		
		Utils utils = new Utils();
		params = utils.parseRequest(request);
//
//		 firstName = params.get("fname");
//		 lastName = params.get("lname");
//		 password = params.get("password");
		
		
		 firstName = request.getParameter("fname");
		 lastName = request.getParameter("lastName");
		 password = request.getParameter("password");
				
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(password);
		
		try{
		if(firstName.equals(null)|| lastName.equals(null) || password.equals(null)){
			try {
				json.put("feedback", "please fill all the required fields");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return;
		}
		if(firstName.equals("")|| lastName.equals("") || password.equals("")){
			try {
				json.put("feedback", "please fill all the required fields");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				result = false;
				json.put("feedback", result);
			} catch (JSONException e) {
				exception = new Utils();			
				exception.writeException("aimbookLogs.txt", e);
			}
			return;
			}
		try {
			pharmacy.setFirst_name(firstName);
			pharmacy.setLast_name(lastName);
			pharmacy.setPassword(password);
			pharmacy.setId();
			result = pharmacy.register(pharmacy);
			json.put("feedback", result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}
		finally{
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
