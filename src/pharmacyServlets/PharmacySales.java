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
 * Servlet implementation class pharmacistSells
 */
@WebServlet("/Sales")
public class PharmacySales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PharmacySales() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject json = new JSONObject();
		PrintWriter writer = response.getWriter(); 
		String productType,distributorId;
		int userId, quantity,unitPrice;
		boolean feedback;
		Pharmacy pharmacy = new Pharmacy();
		Utils exception;
		Map <String,String> params ;		
		Utils utils = new Utils();
		params = utils.parseRequest(request);
			
		
//		 userId = Integer.parseInt(request.getParameter("id"));
//		 productType  = request.getParameter("productType");
//		 unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
//		 quantity = Integer.parseInt(request.getParameter("quantity"));
//		 distributorId = request.getParameter("distributorId");
		
		 userId = Integer.parseInt(params.get("id"));
		 productType  = params.get("productType");
		 unitPrice = Integer.parseInt(params.get("unitPrice"));
		 quantity = Integer.parseInt(params.get("quantity"));
		 distributorId = params.get("distributorId");
		
		 feedback = pharmacy.recordSales(userId, productType, unitPrice, quantity, distributorId);
		
		try {
			json.put("feedback", feedback);
		} catch (JSONException e) {
			exception = new Utils();			
			exception.writeException("aimbookLogs.txt", e);
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
