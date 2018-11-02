package userServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import model.Pharmacy;
import utils.Utils;

/**
 * Servlet implementation class PharmacyLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*A response that tells the browser to allow code from any origin to access a resource*/
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Logging......");
		PrintWriter printWriter = response.getWriter();	
		Map <String,String> params;
		Utils utils = new Utils();
		JSONObject json = new JSONObject();
		
		params = utils.parseRequest(request);
		String userType = params.get("userType");
		String password = params.get("password");
//		String userType = request.getParameter("userType");
//		String password = request.getParameter("password");
		
		System.out.println(userType);
		System.out.println(password);
		
		if(userType != null && password != null)
			System.out.println("data received");
		Pharmacy pharmacy = new Pharmacy();
		
		 json = pharmacy.login(userType, password);
			printWriter.println(json);
			if(json != null)
			System.out.println("Logged.");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
