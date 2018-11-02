package model;

import java.security.SecureRandom;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import database.UserDbConnection;
import utils.FileOperation;

public abstract  class User {

	private int id = -1;
	private String first_name;
	private String last_name;
	private String password;
	
	
	public int getId() {
		return id;
	}
	public void setId(){
		SecureRandom random = new SecureRandom();
		this.id = random.nextInt(99999999);
	}
	public  void retrieveId(int id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public JSONObject login(String userType,String password){
		int ID;
		JSONObject json = new JSONObject();
		UserDbConnection dbConn1,dbConn2;
		dbConn1= new UserDbConnection();
		ID = dbConn1.checkPassword(password);
		dbConn2= new UserDbConnection();
		json = dbConn2.retrieveUserInfo(ID, userType);
		return json;
	}
	
	
	public abstract String toString();
	
	public JSONObject getStock(int userId){
		JSONObject products = new JSONObject();
		int soldQuantity = 0 , purchasedQuantity = 0 ,productQuantity = 0;
		ArrayList<String> salesTransactionIds = new ArrayList<>();
		ArrayList<String> purchasesTransactionIds = new ArrayList<>();
		FileOperation file = new FileOperation();
		String product;
		UserDbConnection dbConnection1,dbConnection2,dbConnection3,dbConnection4;
		JSONObject stock = new JSONObject();
		
		products = file.readFile("productsList.txt");
		
		for( int key = 1; key <= products.length();key++){
			try {
				product = products.get(Integer.toString(key)).toString();
				dbConnection1 =  new UserDbConnection();
				salesTransactionIds = dbConnection1.getSalesTransactionIds(userId, product);
				dbConnection2 = new UserDbConnection();
				soldQuantity = dbConnection2.getRecordedSales(salesTransactionIds);
				dbConnection3 =  new UserDbConnection();
				purchasesTransactionIds = dbConnection3.getPurchaseTransactionIds(userId, product);
				dbConnection4 = new UserDbConnection();
				purchasedQuantity = dbConnection4.getRecordedPurchases(purchasesTransactionIds);
				productQuantity = purchasedQuantity - soldQuantity;
				stock.put(product, productQuantity);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			}
		return stock;
		
	}
	
	/*create tables of 
	 * the productsList.txt */
	public boolean createTables(){
		JSONObject products = new JSONObject();
		FileOperation file = new FileOperation();
		UserDbConnection dbConnection;
		String product;
		boolean result = true;;
		
		products = file.readFile("productsList.txt");
		for( int key = 1; key <= products.length();key++){
			try {
				product = products.get(Integer.toString(key)).toString();
				dbConnection = new UserDbConnection();
				dbConnection.createTables(product);
				}

			 catch (JSONException e) {
				e.printStackTrace();
				 result = false;
			}
			}
		return result;
		
	}
	}
