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
 * Servlet implementation class RecordPurchases
 */
@WebServlet("/PharmacyPurchases")
public class PharmacyPurchases extends HttpServlet {
	private static final long serialVersionUid = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PharmacyPurchases() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*A response that tells the browser to allow code from any origin to access a resource*/
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("recording......");
		
		String productType;
		int id,quantity;
		JSONObject json = new JSONObject();
		PrintWriter writer = response.getWriter();
		Map <String,String> params ;		
		Utils utils = new Utils();
		Utils exception;
		params = utils.parseRequest(request);
		
		
//		 id = Integer.parseInt(params.get("id"));
//		 productType = params.get("productType");
//		 quantity = Integer.parseInt(params.get("quantity"));
		
		 id = Integer.parseInt(request.getParameter("id"));
		 productType = request.getParameter("productType");
		 quantity = Integer.parseInt(request.getParameter("quantity"));
		
		System.out.println(id);
		System.out.println(productType);
		System.out.println(quantity);
		
//		if(id != 0 && productType != null && quantity != 0) {
//			System.out.println("data received");
//		}
		
		Pharmacy pharmacist = new Pharmacy();		
		boolean feedback = pharmacist.recordPurchases(id, productType, quantity);
		try {
			json.put("feedback", feedback);
		} catch (JSONException e) {
			exception = new Utils();			
			exception.writeException("aimbookLogs.txt", e);
		}
		finally{
			writer.println(json);
			System.out.println("products recorded");
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
