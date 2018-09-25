package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Distributor;


public class DbConnection {

	Connection myConnection;
	PreparedStatement statement;
	ResultSet rs;

	
//	public DbConnection(){
//	try {
//		Class.forName("com.mysql.jdbc.Driver");
//		String db = "jdbc:mysql://localhost:3306/distributors_db";
//		 myConnection = DriverManager.getConnection(db, "root", "");
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	}
	
	public DbConnection(String dbName){
		try {
			final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			final String DB_URL = "jdbc:mysql://localhost";
			final String USER = "root";
			final String PSWD = "";
			final String MYSQL_PORT = "3306";
			Class.forName(JDBC_DRIVER);
			String db = DB_URL + ":" + MYSQL_PORT + "/"  + dbName;
			myConnection = DriverManager.getConnection(db, USER,PSWD);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public boolean registerDistributor(Distributor distributor) {
		boolean success = true;
		
		try {
			//develop a query to update the DB
			String query = "INSERT INTO distributors(firstName,lastName,phoneNumber,registrationNumber,uplineRegistrationNumber,uplineSide,password)VALUES(?,?,?,?,?,?,?)";
			//prepare the statement
			statement = myConnection.prepareStatement(query);
			
			statement.setString(1, distributor.getFirstName());
			statement.setString(2, distributor.getLastName());
			statement.setString(3, distributor.getPhoneNumber());
			statement.setString(4, distributor.getRegistrationNumber());
			statement.setString(5, distributor.getUplineregistrationNumber());
			statement.setString(6, distributor.getUplineSide());
			statement.setString(7, distributor.getPassword());
			//execute the statement
			statement.executeUpdate();
			if(myConnection != null)
				myConnection.close();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			success = false;
			e.printStackTrace();
			
		}
		
		return success;
		
	}
	
	public boolean removeDistributor(String registrationNumber) {
		boolean success = false;
		String query ="DELETE FROM distributors WHERE registrationNumber = ?";
		try {
			
			statement = myConnection.prepareStatement(query);
			statement.setString(1, registrationNumber);
			statement.executeUpdate();
			statement.close();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
		
	}
	
	
	public boolean login(String userName, String passWord ) {
		boolean success = false;
		
		String query = "SELECT * FROM distributors";
		try {
			statement = myConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 String dbUserName = rs.getString("userName");
				 String dbPassWord = rs.getString("passWord");
				 
				 if(userName.equals(dbUserName)) {
					 
					 if(passWord.equals(dbPassWord)) {
						 success = true;
						 break;
					 }
					 else
						 success = false;
						 break;
				 }
				 else 
					 success = false;
			 }

			 myConnection.close();
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
		
	}
	
	public boolean changePassword(String registrationNumber,String oldPassWord,String newPassWord) {
		boolean success = false;
		try {
			String query = "SELECT * FROM distributors";
			statement = myConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String DbregistrationNumber = rs.getString("registrationNumber");
				
				if(DbregistrationNumber == null)
					break;
				
				if(registrationNumber.equals(DbregistrationNumber)) {
					query = "UPDATE distributors SET passWord = ? WHERE registrationNumber = ?";
					statement = myConnection.prepareStatement(query);
					statement.setString(1, newPassWord);
					statement.setString(2, registrationNumber);
					statement.executeUpdate();
					success = true;
					break;
				}
			}
			rs.close();
			statement.close();
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return success;
		
	}
	
	public String getregistrationNumber(String userName, String passWord) {
		String registrationNumber = null;
		try {
			String query = "SELECT * FROM distributors";
			statement = myConnection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String DbUserName = rs.getString("userName");
				String DbPassWord = rs.getString("passWord");
				String DbregistrationNumber = rs.getString("registrationNumber");
				if(userName.equals(DbUserName) && passWord.equals(DbPassWord)) {
					registrationNumber = DbregistrationNumber;
					break;
				}
					
			}
			
			myConnection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registrationNumber;
		
	}
	

	public boolean updateUpline(String uplineregistrationNumber,String childregistrationNumber,String childSide) {
		boolean success = false;
		ResultSet rs;
		try {
			
			String query = "SELECT * FROM distributors";
			statement = myConnection.prepareStatement(query);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				String registrationNumber = rs.getString("registrationNumber");
				
				if(registrationNumber.equals(uplineregistrationNumber)) {
					
					if(childSide.equals("left")) {
						query = "UPDATE distributors SET leftChild = ? WHERE registrationNumber = ?";
						statement = myConnection.prepareStatement(query);
						statement.setString(1, childregistrationNumber);
						statement.setString(2, uplineregistrationNumber);
						statement.executeUpdate();
						success = true;
					}
					
					else if (childSide.equals("right")) {
						query = "UPDATE distributors SET rightChild = ? WHERE registrationNumber = ?";
						statement = myConnection.prepareStatement(query);
						statement.setString(1, childregistrationNumber);
						statement.setString(2, uplineregistrationNumber);
						statement.executeUpdate();
						success = true;
					}
					
					break;
				}
				
			}
			myConnection.close();

			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return success;
	}
	
		

		
  public ArrayList<Distributor> getAllDistributors(){
			ArrayList <Distributor> distributors = new ArrayList<Distributor>();
			ResultSet rs;
			
			
			try {
				String query = "SELECT * FROM distributor";
				statement = myConnection.prepareStatement(query);
				rs = statement.executeQuery();
				
				while (rs.next()){
					Distributor distributor = new Distributor();
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String phoneNumber = rs.getString("phoneNumber");
					String registrationNumber = rs.getString("registrationNumber");
					String uplineregistrationNumber = rs.getString("uplineRegistrationNumber");
					String uplineSide = rs.getString("uplineSide");
					String password = rs.getString("password");
					String rightChild = rs.getString("rightChild");
					String leftChild = rs.getString("leftChild");
					
					distributor.setFirstName(firstName);
					distributor.setLastName(lastName);
					distributor.setPhoneNumber(phoneNumber);
					distributor.setRegistrationNumber(registrationNumber);
					distributor.setUplineregistrationNumber(uplineregistrationNumber);
					distributor.setUplineSide(uplineSide);
					distributor.setPassword(password);
					distributor.setLeftChild(leftChild);
					distributor.setRightChild(rightChild);
					
					distributors.add(distributor);
					
				}
				
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return distributors;
			
		}
		
	
}
