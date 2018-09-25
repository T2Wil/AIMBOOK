package database;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Investor;
import model.Pharmacist;
import model.User;

public class UserDbConnection {

	Connection connection;

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost";
	final String USER = "root";
	final String PSWD = "";
	final String MYSQL_PORT = "3306";

	// open the user database connection
	public UserDbConnection(String dbName) {
		try {

			Class.forName(JDBC_DRIVER);
			String db = DB_URL + ":" + MYSQL_PORT + "/" + dbName;
			connection = DriverManager.getConnection(db, USER, PSWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// to be used when creating a new database during signup
	public UserDbConnection() {
		try {

			Class.forName(JDBC_DRIVER);
			String db = DB_URL + ":" + MYSQL_PORT + "/";
			connection = DriverManager.getConnection(db, USER, PSWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Generate a unique User ID */
	public int setId() {
		int id;
		String query,dbID;
		PreparedStatement statement;
		ResultSet rs;
		// will provide 100,000 unique Users IDs
		SecureRandom random = new SecureRandom();
		id = random.nextInt(99999);
		try {
			query = "SELECT * FROM users";
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while(rs.next()){
				dbID = rs.getString("ID");
				if(id == Integer.parseInt(dbID)){
					setId();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
				

	}

	/*
	 * register user details to users_db
	 */
	public boolean signup(User user) {
		boolean result = true;
		try {
			String userType = user instanceof Investor ? "investor":"pharmacist";
			String query = "SELECT * FROM users";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeQuery();
			query = "INSERT INTO users (id,user_name," + "email,phone_number,user_type) " + "VALUES (?,?,?,?,?)";

			statement = connection.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getUser_name());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPhone_number());
			statement.setString(5, userType);
			statement.executeUpdate();
			if (connection != null)
				connection.close();

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
		}
	/*
	 * create user database and register his/her info as an pharmacist
	 */
	public boolean createDatabase(Pharmacist pharmacist) {
		boolean result = true;
		try {
			String query = "CREATE DATABASE IF NOT EXISTS " + pharmacist.getUser_name() + "_db";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			result = false;
		}
		return result;
	}

	/*
	 * create user database and register his/her info as an investor
	 */
	public boolean createDatabase(Investor investor) {
		boolean result = true;
		try {

			String query = "CREATE DATABASE IF NOT EXISTS " + investor.getUser_name() + "_db";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
			if (connection != null)
				connection.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			result = false;
		}
		return result;
	}

	/*
	 * creating a table for an investor
	 * 
	 */
	public boolean createTable_Investor() {
		boolean result = true;
		try {
			String query = "CREATE TABLE IF NOT EXISTS investor" + "(" + "ID VARCHAR(10)," + "date DATE,"
					+ "purchased VARCHAR(10)," + "sold VARCHAR(10)," + "stock VARCHAR(10)" + ")";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean createTable_Pharmacy() {
		boolean result = true;

		PreparedStatement statement;
		try {
			String query = "CREATE TABLE IF NOT EXISTS pharmacy " + "(" + "id VARCHAR(10)," + "date DATE,"
					+ "product_code VARCHAR(10)" + ")";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	/*
	 * add investor information into investors database
	 */
	public boolean addToInvestorsDatabase(Investor investor) {
		boolean result = true;
		PreparedStatement statement;
		try {
			String query = "INSERT INTO investors (id,user_name,first_name," + "last_name,email,phone_number,password) "
					+ "VALUES (?,?,?,?,?,?,?)";

			statement = connection.prepareStatement(query);
			statement.setInt(1, investor.getId());
			statement.setString(2, investor.getUser_name());
			statement.setString(3, investor.getFirst_name());
			statement.setString(4, investor.getLast_name());
			statement.setString(5, investor.getEmail());
			statement.setString(6, investor.getPhone_number());
			statement.setString(7, investor.getPassword());
			statement.executeUpdate();

			result = true;
			if (connection != null)
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/*
	 * adding pharmacist information to pharmacists database
	 */
	public boolean addToPharmacistsDatabase(Pharmacist pharmacist) {
		boolean result = true;
		PreparedStatement statement;
		try {
			String query = "INSERT INTO pharmacists (id,user_name,first_name,"
					+ "last_name,email,phone_number,password) " + "VALUES (?,?,?,?,?,?,?)";

			statement = connection.prepareStatement(query);
			statement.setInt(1, pharmacist.getId());
			statement.setString(2, pharmacist.getUser_name());
			statement.setString(3, pharmacist.getFirst_name());
			statement.setString(4, pharmacist.getLast_name());
			statement.setString(5, pharmacist.getEmail());
			statement.setString(6, pharmacist.getPhone_number());
			statement.setString(7, pharmacist.getPassword());
			statement.executeUpdate();
			result = true;
			if (connection != null)
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	/*
	 * Remove investor info from users_db	 */
	public boolean removeInvestorFromUsers_db(Investor investor) {
		boolean result = true;
		String query;
		PreparedStatement statement = null;
		try {
			query = "DELETE FROM users WHERE user_name=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, investor.getUser_name());
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;

	}
	/*
	 * Remove pharmacist info from users_db	 */
	public boolean removePharmacistFromUsers_db(Pharmacist pharmacist) {
		boolean result = true;
		String query;
		PreparedStatement statement = null;
		try {
			query = "DELETE FROM users WHERE user_name=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, pharmacist.getUser_name());
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;

	}
	/*Remove investor details from investors_db*/
	public boolean removeInvestorFromInvestors_db(Investor investor) {
		boolean result = true;
		String query;
		PreparedStatement statement = null;
		try {
			query = "DELETE FROM investors WHERE user_name=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, investor.getUser_name());
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;
	}
	/*Remove pharmacist details from pharmacists_db*/
	public boolean removePharmacistFromPharmacists_db(Pharmacist pharmacist) {
		boolean result = true;
		String query;
		PreparedStatement statement = null;
		try {
			query = "DELETE FROM pharmacists WHERE user_name=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, pharmacist.getUser_name());
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;
	}
/*Drop/delete database of an investor*/
	public boolean dropInvestorDb(Investor investor) {
		boolean result = true;
		String query;
		PreparedStatement statement = null;
		try {
			query = "DROP DATABASE ?";
			statement = connection.prepareStatement(query);
			String dbName = investor.getUser_name() + "_db";
			statement.setString(1, dbName);
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;
	}
	/*Drop/delete database of a pharmacist*/
	public boolean dropPharmacistDb(Pharmacist pharmacist) {
		boolean result = true;
		String query;
		PreparedStatement statement = null;
		try {
			query = "DROP DATABASE ?";
			statement = connection.prepareStatement(query);
			String dbName = pharmacist.getUser_name() + "_db";
			statement.setString(1, dbName);
			statement.executeUpdate();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;
	}

	public String login(String userName, String password) {
		String db_user_name = null;
		String db_email = null;
		String db_password = null;
		String db_Id = null;

		try {
			String query = "SELECT * FROM users";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				 db_user_name = rs.getString("user_name");
				 db_email = rs.getString("email");
				 db_password = rs.getString("password");
				 db_Id = rs.getString("ID");

				if (userName.equals(db_user_name) || userName.equals(db_email)) {
					if (password.equals(db_password)) {
						break;
					}
				}
			}
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db_Id;

	}

	public Investor retrieveInvestorFromDatabase(String dbName) {
		Investor investor = new Investor();
		int id = 0;
		int sold = 0;
		int purchased = 0;
		int stock = 0;

		try {
			String query = "SELECT * FROM investor ORDER BY purchased DESC LIMIT 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getInt("ID");
				sold = rs.getInt("sold");
				purchased = rs.getInt("purchased");
				stock = rs.getInt("stock");
				investor.retrieveId(id);
				investor.setSold(sold);
				investor.setPurchased(purchased);
				investor.setStock(stock);
			}
			if (connection != null)
				connection.close();

			query = "SELECT * FROM users";
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {
				if (investor.getId() == rs.getInt("ID")) {
					String user_name = rs.getString("user_name");
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String email = rs.getString("email");
					String phone_number = rs.getString("phone_number");
					String password = rs.getString("password");

					investor.setUser_name(user_name);
					investor.setFirst_name(first_name);
					investor.setLast_name(last_name);
					investor.setEmail(email);
					investor.setPhone_number(phone_number);
					investor.setPassword(password);

				}
			}

			if (connection != null)
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return investor;
	}

	public boolean recordInvestorTransactionsToDb(Investor investor) {
		boolean result = false;

		try {
			String query = "INSERT INTO investor (ID,date,purchased,sold,stock) " + "VALUES (?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, investor.getId());
			statement.setString(2, investor.getDate());
			statement.setInt(3, investor.getPurchased());
			statement.setInt(4, investor.getSold());
			statement.setInt(5, investor.getStock());

			statement.executeUpdate();
			result = true;
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public boolean recordPharmacistTransactionsToDb(Pharmacist pharmacist) {
		boolean result = false;

		try {
			String query = "INSERT INTO pharmacy (ID,date,product_code) " + "VALUES (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, pharmacist.getId());
			statement.setString(2, pharmacist.getDate());
			statement.setString(3, pharmacist.getProduct_code());
			statement.executeUpdate();
			result = true;
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public Pharmacist retrievePharmacistFromDatabase(String dbName) {
		Pharmacist pharmacist = new Pharmacist();
		int id = 0;
		String product_code = null;

		try {
			String query = "SELECT * FROM pharmacy ORDER BY product_code DESC LIMIT 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getInt("ID");
				product_code = rs.getString("product_code");
				pharmacist.retrieveId(id);
				pharmacist.setProduct_code(product_code);
			}
			// get data from the users_db corresponding to the pharmacist ID
			dbName = "users_db";
			String db = DB_URL + ":" + MYSQL_PORT + "/" + dbName;
			connection = DriverManager.getConnection(db, USER, PSWD);
			query = "SELECT * FROM users";
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {
				if (pharmacist.getId() == rs.getInt("ID")) {
					String user_name = rs.getString("user_name");
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String email = rs.getString("email");
					String phone_number = rs.getString("phone_number");
					String password = rs.getString("password");

					pharmacist.setUser_name(user_name);
					pharmacist.setFirst_name(first_name);
					pharmacist.setLast_name(last_name);
					pharmacist.setEmail(email);
					pharmacist.setPhone_number(phone_number);
					pharmacist.setPassword(password);

				}
			}

			if (connection != null)
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pharmacist;
	}

	// pharmacist selling a product
	public boolean sell(String product_code) {
		boolean result = false;
		try {
			String query = "DELETE FROM pharmacy WHERE product_code = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, product_code);
			statement.executeUpdate();
			result = true;
			if (connection != null)
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
