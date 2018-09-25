package controller;

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
 * Servlet implementation class Pharmacy_sell
 */
@WebServlet("/Pharmacy_sell")
public class Pharmacy_sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pharmacy_sell() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter(); 
		JSONObject json = new JSONObject();
		 Map <String,String> params ;
		 Utils utils =  new Utils();
		 
		 params = utils.parseRequest(request);
		 String user_name = params.get("user_name");
		String product_code = params.get("code");
		if(user_name == null || product_code == null)
			return;
		Pharmacist pharmacy = new Pharmacist();
		boolean feedback = pharmacy.sell(user_name, product_code);
		try {
			json.put("feedback", feedback);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		writer.println(feedback);
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
