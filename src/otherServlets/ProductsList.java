package otherServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import utils.FileOperation;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/ProductsList")
public class ProductsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*A response that tells the browser to allow code from any origin to access a resource*/
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Displaying registered products.....");
		PrintWriter writer = response.getWriter();
		JSONObject json = new JSONObject();
		FileOperation file = new FileOperation();
		//include file extension
		json = file.readFile("productsList.txt");
		writer.print(json); 
		System.out.println("products displayed.");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin", "*");

		doGet(request, response);
	}

}
