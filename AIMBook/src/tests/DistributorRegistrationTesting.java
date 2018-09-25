package tests;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Distributor;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class DistributorRegistrationTesting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistributorRegistrationTesting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		String registrationNumber = request.getParameter("registrationNumber");
		String uplineregistrationNumber = request.getParameter("uplineRegistrationNumber");
		String uplineSide = request.getParameter("uplineSide");
		String dbName = request.getParameter("dbName");
		String password = request.getParameter("password");
		
		Distributor distributor = new Distributor ();
		
		if(firstName != null && lastName  != null &&  phoneNumber != null
			 && registrationNumber != null && uplineregistrationNumber != null && uplineSide != null
					 && dbName != null && password != null) {
			distributor.setFirstName(firstName);
			distributor.setLastName(lastName);
			distributor.setPhoneNumber(phoneNumber);
			distributor.setRegistrationNumber(registrationNumber);
			distributor.setUplineregistrationNumber(uplineregistrationNumber);
			distributor.setUplineSide(uplineSide);
			distributor.setDbName(dbName);
			distributor.setPassword(password);
			
		}
		
		boolean feedback1 = distributor.registerDistributor(distributor);
				

		System.out.println("firstName : " + firstName);
		System.out.println("lastName : " + lastName);
		System.out.println("phoneNumber : " + phoneNumber);
		System.out.println("registrationNumber : " + registrationNumber);
		System.out.println("uplineregistrationNumber : " + uplineregistrationNumber);
		System.out.println("uplineSide : " + uplineSide);
		System.out.println("password : " + password);
		
		System.out.println("Distributor registration status : " + feedback1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
