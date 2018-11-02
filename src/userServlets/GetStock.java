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
 * Servlet implementation class GetStock
 */
@WebServlet("/getstock")
public class GetStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		int userId;
		 Pharmacy user = new Pharmacy();
		JSONObject stock = new JSONObject();
		PrintWriter writer = response.getWriter();
		Map <String,String> params ;		
		Utils utils = new Utils();
		params = utils.parseRequest(request);
		
		 userId = Integer.parseInt(params.get("userId"));
//		 userId = Integer.parseInt(request.getParameter("userId"));
			user.createTables(); //
			stock = user.getStock(userId);
			writer.println(stock);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
