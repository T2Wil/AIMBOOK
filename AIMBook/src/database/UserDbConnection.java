package database;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import model.Investor;
import model.Pharmacy;
import model.User;
import utils.FileOperation;


/* DEPLOYMENT/LOCAL CHANGES
 * database ip address
 * database password
 * */
public class UserDbConnection {

	Connection connection;

//	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	final String DB_URL = "jdbc:mysql://database";
//	final String USER = "root";
//	final String PSWD = "root";
//	final String MYSQL_PORT = "3306";
	
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost";
	final String USER = "root";
	final String PSWD = "";
	final String MYSQL_PORT = "3306";

	// open the user database connection
	public UserDbConnection() {
		String db;
		try {
			Class.forName(JDBC_DRIVER);
			 db = DB_URL + ":" + MYSQL_PORT + "/";
			connection = DriverManager.getConnection(db, USER, PSWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*User registration 
	 * insert into users_db.users the ID and password
	 * */
	public boolean registerUser(String dbName,int ID, String password){
		PreparedStatement statement;
		boolean result = true;
		String query;
		try {
			query = "CREATE DATABASE IF NOT EXISTS " + dbName;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "CREATE TABLE IF NOT EXISTS " + dbName
					+ ".users(ID VARCHAR(10) NOT NULL,password VARCHAR(10) NOT NULL)";
			statement= connection.prepareStatement(query);
			statement.executeUpdate();
			
			query = "INSERT INTO " + dbName + ".users (ID,password) "
					+ "VALUES(?,?)";
			statement= connection.prepareStatement(query);
			statement.setString(1, Integer.toString(ID));
			statement.setString(2, password);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		finally{
				try {
					if (connection != null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	/*pharmacy registration
	 *insert into users_db.pharmacy the pharmacist info
	 *the ID,fn,ln,dbName
	 **/
	public boolean AddPharmacy(String dbName,Pharmacy pharmacist){
		PreparedStatement statement;
		boolean result = true;
		String query;
		try {
			query = "CREATE DATABASE IF NOT EXISTS " + dbName;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "CREATE TABLE IF NOT EXISTS "
					 + dbName
					+ ".pharmacy(ID VARCHAR(10) NOT NULL,`First name` VARCHAR(50) NOT NULL,"
					+ "`Last name` VARCHAR(50) NOT NULL,`Dbname` VARCHAR(50) NOT NULL)";
			statement= connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "INSERT INTO " + dbName 
					 +".pharmacy (ID,`First name`,`Last name`,DbName)"
					+ "VALUES(?,?,?,?)";
			statement= connection.prepareStatement(query);
			statement.setString(1, Integer.toString(pharmacist.getId()));
			statement.setString(2, pharmacist.getFirst_name());
			statement.setString(3, pharmacist.getLast_name());
			statement.setString(4, pharmacist.getLast_name()+"_db");
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		finally{
			try {
				if (connection != null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		return result;
	}
	

	
	/*Record changes in the stock
	 * stock_db.productType
	 * */
	public boolean updateStock(String dbName,String userId,String productType,String transactionType,String transactionId){
		boolean result = true;
		PreparedStatement statement;
		String query;
		try {
			query = "CREATE DATABASE IF NOT EXISTS " + dbName;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "CREATE TABLE IF NOT EXISTS " + dbName + "." + productType
					+ "(Date VARCHAR(30) NOT NULL,"
					+ "User_ID VARCHAR(30) NOT NULL,"
					+ "transaction_ID VARCHAR(30) NOT NULL,"
					+ "transaction_type VARCHAR(30) NOT NULL)";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "INSERT INTO " + dbName + "." + productType + 
					"(date,user_ID,transaction_ID,transaction_Type)"
					+ "VALUES(CURRENT_TIMESTAMP,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, userId);
			statement.setString(2, transactionId);
			statement.setString(3, transactionType);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		finally{		
				try {
					if(connection != null)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} 
		return result;	
	}
	
	/*delete a row (transaction recorded)
	 * in the stock_db.productType*/
	public boolean deleteTransaction(String dbName,String productType,int transactionId){
		boolean result = true;
		PreparedStatement statement;
		String query;
		try {
			query = "CREATE DATABASE IF NOT EXISTS " + dbName;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "DELETE FROM " + productType +
					"WHERE transaction_ID = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, Integer.toString(transactionId));
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		finally{		
				try {
					if(connection != null)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} 
		return result;
		
	}
	/*Record purchases made
	 * in the transactions_db.purchases
	 * */
	public boolean recordPurchases(String dbName,String product_Type,String transactionId, String quantity){
		boolean result = true;
		PreparedStatement statement;
		String query;
		try {
			query = "CREATE DATABASE IF NOT EXISTS " + dbName;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "CREATE TABLE IF NOT EXISTS " + dbName
					+ ".purchases"
					+"(date VARCHAR(30) NOT NULL,"
					+ "transaction_ID VARCHAR(20) NOT NULL,"
					+"product_Type VARCHAR(30) NOT NULL,"
					+ "quantity VARCHAR(20) NOT NULL)";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "INSERT INTO " + dbName 
					 +".purchases (date,transaction_ID,product_Type,quantity)"
					+ "VALUES(CURRENT_TIMESTAMP,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1,transactionId);
			statement.setString(2,product_Type);
			statement.setString(3,quantity);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		finally{
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
		
		
		
	}
	/*Record sales made
	 * in the transactions_db.sales
	 * */
	public boolean recordSales(String dbName,String transactionId,String productType,String quantity,String unitPrice,String distributorId){
		boolean result = true;
		PreparedStatement statement;
		String query,query1,query2;
		try {
			query = "CREATE DATABASE IF NOT EXISTS " + dbName;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "CREATE TABLE IF NOT EXISTS " + dbName 
					+ ".sales"
					+ "(date VARCHAR(30) NOT NULL,"
					+ "transaction_ID VARCHAR(20) NOT NULL,"
					+ "product_type VARCHAR(30) NOT NULL,"
					+ "quantity VARCHAR(20) NOT NULL,"
					+ "unit_price VARCHAR(20) NOT NULL,"
					+ "distributor_ID VARCHAR(20) NULL)";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query1 = "INSERT INTO "+ dbName
			 		+ ".sales (date,transaction_ID,product_type,quantity,unit_price,distributor_ID)"
					+ "VALUES(CURRENT_TIMESTAMP,?,?,?,?,?)";
			 query2 = "INSERT INTO "+ dbName
				 		+ ".sales (date,transaction_ID,product_type,quantity,unit_price)"
						+ "VALUES(CURRENT_TIMESTAMP,?,?,?,?)";
			 query = distributorId.equals("")? query2:query1;
			 
			statement = connection.prepareStatement(query);
			statement.setString(1, transactionId);
			statement.setString(2, productType);
			statement.setString(3, quantity);
			statement.setString(4, unitPrice);
			if(!distributorId.equals(""))
				statement.setString(5, distributorId);
			statement.executeUpdate();

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		finally{
			
				try {
					if(connection != null)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
		
	}
	
	/*while Login
	 * check entered password if matches one of the users_db.users
	 * return the user's ID
	 * */
	public int checkPassword(String password){
		PreparedStatement statement;
		String dbPswd,query;
		int id = 0;
		try {
			 query = "SELECT * FROM users_db.users ";
			statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				dbPswd = rs.getString("password");
				if (dbPswd.equals(password)){
					id= Integer.parseInt(rs.getString("ID"));
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if (connection != null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		return id;
	}
	/*get the user info(Fn,Ln,dbName)
	 *  passing the user ID
	 *from the users_db.userType(pharmacy/investor)
	 **/
	public JSONObject retrieveUserInfo(int ID,String userType){
		User user = userType.equals("pharmacy")? new Pharmacy() : new Investor();
		PreparedStatement statement;
		int dbID = -1;
		String dbFirstName = null;
		String dbLastName = null;
		String userDbName = null;
		String query;
		JSONObject json = new JSONObject();
		
		try {
			 query = "SELECT * FROM users_db."+userType + " WHERE ID=?";
			statement = connection.prepareStatement(query);
			statement.setString(1,Integer.toString(ID));
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				dbID = Integer.parseInt(rs.getString("ID"));
					dbFirstName = rs.getString("First name");
					dbLastName = rs.getString("Last name");
					userDbName = rs.getString("DbName");
			}
			user.setFirst_name(dbFirstName);
			user.setLast_name(dbLastName);
			user.retrieveId(dbID);			
			json.put("ID", user.getId());
			json.put("firstName", user.getFirst_name());
			json.put("lastName", user.getLast_name());
			json.put("DbName", userDbName);

			
			} catch (Exception e) {

				e.printStackTrace();
			}
		
		finally{
			try {
				if(connection != null)
					connection.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return json;
		
			
	}
	
	/*
	 * create new database
	 * */
	public boolean createDatabase(String dbName) {
		boolean result = true;
		try {
			String query = "CREATE DATABASE IF NOT EXISTS " + dbName;

			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			result = false;
		}
		finally{
			try {
				if(connection != null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/*Drop/delete database of a pharmacist*/
	/*
	 * dbName = pharmacist.getLast_name() + "_db";
	 */
	public boolean dropDb(String dbName) {
		boolean result = true;
		String query;
		PreparedStatement statement = null;
		try {
			query = "DROP DATABASE " + dbName;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		finally{
			try {
				if(connection != null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/*delete user from the users_db.users*/
	public boolean deleteUser(String dbName,int ID){
		PreparedStatement statement ;
		boolean result = true;
		String query;
		try {
			 query = "DELETE FROM " + dbName +
					 ".users WHERE ID=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, Integer.toString(ID));
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = true;
		}
		finally{
			try {
				if(connection != null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	/*delete pharmacy from the users_db.pharmacy*/
	public boolean deletePharmacy(String dbName,int ID){
		PreparedStatement statement ;
		boolean result = true;
		String query;
		try {
			 query = "DELETE FROM " + dbName +
					 ".pharmacy WHERE ID=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, Integer.toString(ID));
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = true;
		}
		finally{
			try {
				if(connection != null)
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public ArrayList<String> getPurchaseTransactionIds(String userId,
			String productType){
		
		String query,transactionId;
		PreparedStatement statement;
		ResultSet rs;
		ArrayList <String> transactionIds = new ArrayList<>();
		
		try {
			query = "SELECT * FROM stock_db." + productType
					+ " WHERE user_ID = ?"
					+ "AND transaction_Type = 'purchase'";
			statement = connection.prepareStatement(query);
			statement.setString(1, userId);
			rs = statement.executeQuery();
			while(rs.next()){
				transactionId = rs.getString("transaction_ID");
				transactionIds.add(transactionId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
				return transactionIds;
	
	}
	public ArrayList<String> getSalesTransactionIds(String userId,
			String productType){
		String query,transactionId;
		PreparedStatement statement;
		ResultSet rs;
		ArrayList <String> transactionIds = new ArrayList<>() ;
		
		try {
			query = "SELECT * FROM stock_db." + productType
					+ " WHERE user_ID = ?"
					+ "AND transaction_Type = 'sales'";
			statement = connection.prepareStatement(query);
			statement.setString(1, userId);
			rs = statement.executeQuery();
			while(rs.next()){
				transactionId = rs.getString("transaction_ID");
				transactionIds.add(transactionId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
				return transactionIds;
	
	}
	
	public int getRecordedPurchases(ArrayList<String> transIds){
		String query;
		int quantity=0;
		PreparedStatement statement;
		ResultSet rs;
		StringBuilder transactionIds = new StringBuilder();
		if(transIds.isEmpty())
			return 0;
		for(String id:transIds){
			transactionIds.append(id+",");
		}
		transactionIds.deleteCharAt(transactionIds.length()-1);
		
		try {

			query = "SELECT * FROM transactions_db.purchases WHERE transaction_ID  IN (" + transactionIds+")";
			statement = connection.prepareStatement(query);
			//statement.setString(1, transactionId);
			rs = statement.executeQuery();
			while (rs.next()){
				
				quantity += Integer.parseInt(rs.getString("quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return quantity;
		
	}
	public int getRecordedSales(ArrayList<String> transIds){
		String query;
		int quantity=0;
		PreparedStatement statement;
		ResultSet rs;
		StringBuilder transactionIds = new StringBuilder();
		if(transIds.isEmpty())
			return 0;
		for(String id:transIds){
			transactionIds.append(id+",");
		}
		transactionIds.deleteCharAt(transactionIds.length()-1);// remove the last comma	
		try {

			query = "SELECT * FROM transactions_db.sales WHERE transaction_ID  IN (" + transactionIds+")";
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()){
				
				quantity += Integer.parseInt(rs.getString("quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return quantity;
		
	}
	

	/*create stock tables if not exist
	 * in stock_db*/
	public boolean createTables(String product){
		boolean result = true;
		String query;
		PreparedStatement statement;
		try {
			query = "CREATE DATABASE IF NOT EXISTS stock_db" ;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			 query = "CREATE TABLE IF NOT EXISTS stock_db."+ product
					+ "(Date VARCHAR(30) NOT NULL,"
					+ "User_ID VARCHAR(30) NOT NULL,"
					+ "transaction_ID VARCHAR(30) NOT NULL,"
					+ "transaction_type VARCHAR(30) NOT NULL)";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		}
			catch (SQLException e) {
				result = false;
				e.printStackTrace();
			}
			finally{
				if(connection != null){
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			
		
	}
		return result;
		
	
}
	}
