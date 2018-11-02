package investorServlets;

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

import model.Investor;
import utils.Utils;

/**
 * Servlet implementation class InvesterSignup
 */
@WebServlet("/InvestorSignup")
public class InvestorSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvestorSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 PrintWriter writer = response.getWriter(); 
//		 JSONObject json = new JSONObject();
//		 Map <String,String> params ;
//		 Utils utils =  new Utils();
//		 
//		 params = utils.parseRequest(request);
//		 String user_name = params.get("UserName");
//		 String first_name = request.getParameter("fname_") ;
//		 String last_name = request.getParameter("lname");
//		 String email = request.getParameter("email");
//		 String phone_number = request.getParameter("tel");
//		 String password = request.getParameter("password");
//		 
//		 
//		 if(user_name == null ||first_name == null ||last_name == null 
//				 ||email == null ||phone_number == null ||password == null )
//		 {
//			 return;
//		 }
//			 
//		 Investor investor = new Investor();
//		 investor.setId();
//		 investor.setUser_name(user_name);
//		 investor.setFirst_name(first_name);
//		 investor.setLast_name(last_name);
//		 investor.setEmail(email);
//		 investor.setPhone_number(phone_number);
//		 investor.setPassword(password);
//		 writer.println(investor);
//		 boolean feedback = investor.signup(investor);
//		 try {
//			json.put("feedback",feedback);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 finally{
//			 writer.println(json);
//		 }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
