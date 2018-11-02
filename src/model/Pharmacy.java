package model;

import database.UserDbConnection;
import utils.MathOperation;

public class Pharmacy extends User{

	private String Product_code;//product code
	
	public String getProduct_code() {
		return Product_code;
	}

	public void setProduct_code(String product_code) {
		Product_code = product_code;
	}
	
	@Override
	public String toString() {
		return "Pharmacy [Product_code=" + Product_code + ", getId()=" + getId() + ", getFirst_name()="
				+ getFirst_name() + ", getLast_name()=" + getLast_name() + ", getPassword()=" + getPassword() + "]";
	}
	public boolean register(Pharmacy pharmacy){
		boolean result = false;
		int ID ;
		String password,dbName;
		password =  pharmacy.getPassword();
		ID = pharmacy.getId();
		dbName = pharmacy.getFirst_name()+"_db";
		UserDbConnection DbConn1,DbConn2,DbConn3 = null,DbConn4 = null,DbConn5 = null,DbConn6 = null;
		
		DbConn1= new UserDbConnection();
		if(DbConn1.registerUser("users_db",ID,password)){
			DbConn2 = new UserDbConnection();
			if(DbConn2.AddPharmacy("users_db",pharmacy)){
				DbConn3= new UserDbConnection();
				if(DbConn3.createDatabase(dbName)){
					result = true;
				}
			}
		}
			
		if(result != true){
			DbConn4= new UserDbConnection();
			DbConn4.deleteUser("users_db",ID);
			DbConn5= new UserDbConnection();
			DbConn5.deletePharmacy("users_db",ID);
			DbConn6= new UserDbConnection();
			DbConn6.dropDb(dbName);
		}
				
		return result;
	}

	public boolean recordPurchases(int userId, String productType,int quantity){
		
		
		MathOperation operation;
		boolean result = false;
		int transactionId;
//		String transactionIdToString;
		UserDbConnection dbCon1,dbCon2,dbCon3 = null;
		
		operation = new MathOperation();
		transactionId = operation.generateSecureNumber();
//		transactionIdToString = Integer.toString(transactionId);
		dbCon1 = new UserDbConnection();
		if(dbCon1.updateStock("stock_db",userId, productType, "purchase", transactionId)){
			dbCon2 = new UserDbConnection();
			if(dbCon2.recordPurchases("transactions_db",productType,transactionId, quantity) == false){
				//delete the recorded transaction in the stock_db
				dbCon3 = new UserDbConnection();
				dbCon3.deleteTransaction("stock_db",productType, transactionId);
			}
			else	
				result = true;	
		}
		return result;

	}
	
	public boolean recordSales(int userId, String productType,int unitPrice,int quantity,String distributorId){
		MathOperation operation;
		boolean result = false;
		int transactionId;
//		String transactionIdToString;
		UserDbConnection dbCon1,dbCon2,dbCon3 = null;
		
		operation = new MathOperation();
		transactionId = operation.generateSecureNumber();
//		transactionIdToString = Integer.toString(transactionId);
		dbCon1 = new UserDbConnection();
		if(dbCon1.updateStock("stock_db",userId, productType, "sales", transactionId)){
			dbCon2 = new UserDbConnection();
			System.out.println("transactionId:" + transactionId);
			System.out.println("productType:" + productType);
			System.out.println("quantity:" + quantity);
			System.out.println("unitPrice:" + unitPrice);
			System.out.println("distributorId:" + distributorId);

			if(dbCon2.recordSales("transactions_db",transactionId,productType, quantity, unitPrice, distributorId) == false){
				//delete the recorded transaction in the stock_db
				dbCon3.deleteTransaction("stock_db",productType, transactionId);
			}
			else	
				result = true;	
		}
		return result;
	}
	
	
	
	
}
