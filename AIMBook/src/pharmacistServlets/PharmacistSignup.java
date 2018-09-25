package pharmacistServlets;

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

import model.Pharmacist;
import utils.Utils;

/**
 * Servlet implementation class PharmacistSignup
 */
@WebServlet("/PharmacistSignup")
public class PharmacistSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PharmacistSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		PrintWriter writer = response.getWriter();
		Map <String,String> params ;		
		Utils utils = new Utils();
		params = utils.parseRequest(request);
		String user_name = params.get("userName");
		 String first_name = params.get("firstName") ;
		 String last_name = params.get("lastName");
		 String email = params.get("email");
		 String phone_number = params.get("phoneNumber");
		 String password = params.get("password");
		 
		 if(user_name == null ||first_name == null ||last_name == null 
				 ||email == null ||phone_number == null ||password == null )
			 return;
		 Pharmacist pharmacist = new Pharmacist();
		 pharmacist.setId();
		 pharmacist.setUser_name(user_name);
		 pharmacist.setFirst_name(first_name);
		 pharmacist.setLast_name(last_name);
		 pharmacist.setEmail(email);
		 pharmacist.setPhone_number(phone_number);
		 pharmacist.setPassword(password);
		 writer.println(pharmacist);
		 boolean feedback = pharmacist.signup(pharmacist);
		 try {
			json.put("feedback", feedback);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
