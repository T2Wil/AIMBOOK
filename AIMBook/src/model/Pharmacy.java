package model;

import database.UserDbConnection;

public class Pharmacist extends User{

	private String Product_code;//product code
	
	public String getProduct_code() {
		return Product_code;
	}

	public void setProduct_code(String product_code) {
		Product_code = product_code;
	}
	
	@Override
	public String toString() {
		return "Pharmacist [Product_code=" + Product_code + ", getId()=" + getId() + ", getUser_name()="
				+ getUser_name() + ", getFirst_name()=" + getFirst_name() + ", getLast_name()=" + getLast_name()
				+ ", getEmail()=" + getEmail() + ", getPhone_number()=" + getPhone_number() + ", getPassword()="
				+ getPassword() + ", getDate()=" + getDate() + "]";
	}

	public boolean signup(Pharmacist pharmacist) {
		boolean result = false;
		boolean signupStatus = false;
		boolean createTable_PharmacistStatus = false;
		boolean addUserTypeToUsers_dbStatus = false;
		boolean addToPharmacistsDatabaseStatus = false;
		boolean createDatabaseStatus = false;
		UserDbConnection usersDb,investorsDb,investorDb;
		 usersDb = new UserDbConnection("users_db");
		 investorsDb = new UserDbConnection("pharmacists_db");
		 investorDb = new UserDbConnection();
		
		signupStatus = usersDb.signup(pharmacist);
		/*Sufficient condition to test re-registration*/
		if(!signupStatus)
			return false;
		createDatabaseStatus = investorDb.createDatabase(pharmacist);

		if (createDatabaseStatus) {
			String dbName = pharmacist.getUser_name() + "_db";
			UserDbConnection investDb = new UserDbConnection(dbName);
			createTable_PharmacistStatus = investDb.createTable_Investor();
				addToPharmacistsDatabaseStatus = investorsDb.addToPharmacistsDatabase(pharmacist);
				if (addToPharmacistsDatabaseStatus)
					result = true;
		}
		
		if(result == false){
			if(signupStatus || addUserTypeToUsers_dbStatus)
				usersDb.removePharmacistFromUsers_db(pharmacist);
			if(createDatabaseStatus || createTable_PharmacistStatus)
				usersDb.dropPharmacistDb(pharmacist);
			if(addToPharmacistsDatabaseStatus)
				investorsDb.removePharmacistFromPharmacists_db(pharmacist);
		}
		
		return result;
	}

	//record the new pharmacist product to database
	public boolean record(Pharmacist pharmacist){
		UserDbConnection userDb = new UserDbConnection(pharmacist.getUser_name());
		return userDb.recordPharmacistTransactionsToDb(pharmacist);
	}
	
	public boolean sell(String dbName,String product_code){
		UserDbConnection userDb = new UserDbConnection(dbName);
		return userDb.sell(product_code);
		}

	//load fields data from database (synchronising data on both database and client app)
		public Pharmacist retrievePharmacistFromDatabase(String userName){
			String dbName = userName + "_db";
			UserDbConnection userDb = new UserDbConnection(userName);
			return userDb.retrievePharmacistFromDatabase(dbName);
				
			}	
	
	
}
