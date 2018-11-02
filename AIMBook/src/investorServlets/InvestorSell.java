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
 * Servlet implementation class InvestorSell
 */
@WebServlet("/InvestorSell")
public class InvestorSell extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvestorSell() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		JSONObject json = new JSONObject();
//		PrintWriter writer = response.getWriter();
//		Map <String,String> params ;		
//		Utils utils = new Utils();
//		params = utils.parseRequest(request);
//		
//		String userName = params.get("user_name");
//		String number = params.get("number");
//
//		if(userName == null || number == null)
//			return;
//		Investor investor = new Investor();
//		Investor db_investor = new Investor();
//		
//		investor = db_investor.retrieveInvestorFromDatabase(userName);
//		writer.println(investor);
//		investor.sell(Integer.parseInt(number));
//		boolean feedback = investor.recordInvestorTransactionsToDb(investor);
//		try {
//			json.put("feedback", feedback);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally{
//		writer.println(json);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
