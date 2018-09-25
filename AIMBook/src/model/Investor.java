package model;

import database.UserDbConnection;

public class Investor extends User {

	private int sold = 0;
	private int purchased = 0;
	private int stock = 0;

	public void setSold(int sold) {
		this.sold = sold;
	}

	public int getSold() {
		return sold;
	}

	public void setPurchased(int purchased) {
		this.purchased = purchased;
	}

	public int getPurchased() {
		return purchased;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return "Investor [sold=" + sold + ", purchased=" + purchased + ", stock=" + stock + ", getId()=" + getId()
				+ ", getUser_name()=" + getUser_name() + ", getFirst_name()=" + getFirst_name() + ", getLast_name()="
				+ getLast_name() + ", getEmail()=" + getEmail() + ", getPhone_number()=" + getPhone_number()
				+ ", getPassword()=" + getPassword() + ", getDate()=" + getDate() + "]";
	}

	public boolean signup(Investor investor) {
		boolean result = false;
		boolean signupStatus = false;
		boolean createTable_InvestorStatus = false;
		boolean addUserTypeToUsers_dbStatus = false;
		boolean addToInvestorsDatabaseStatus = false;
		boolean createDatabaseStatus = false;
		
		UserDbConnection usersDb,investorsDb,investorDb,investDb;
		
		 usersDb = new UserDbConnection("users_db");
		 investorsDb = new UserDbConnection("investors_db");
		 investorDb = new UserDbConnection();
		
		signupStatus = usersDb.signup(investor);
		/*Sufficient condition to test re-registration*/
		if(!signupStatus)
			return false;
		createDatabaseStatus = investorDb.createDatabase(investor);

		if (createDatabaseStatus) {
			String dbName = investor.getUser_name() + "_db";
			 investDb = new UserDbConnection(dbName);
			createTable_InvestorStatus = investDb.createTable_Investor();
			addToInvestorsDatabaseStatus = investorsDb.addToInvestorsDatabase(investor);
			if (addToInvestorsDatabaseStatus)
				result = true;
		}
		
		if(result == false){
			if(signupStatus || addUserTypeToUsers_dbStatus)
				usersDb.removeInvestorFromUsers_db(investor);
			if(createDatabaseStatus || createTable_InvestorStatus)
				usersDb.dropInvestorDb(investor);
			if(addToInvestorsDatabaseStatus)
				investorsDb.removeInvestorFromInvestors_db(investor);
		}
		
		return result;
	}

	public boolean purchase(int number) {

		purchased += number;
		// update the stock
		stock += number;

		return true;

	}

	public boolean sell(int number) {
		boolean result = false;
		if (number <= stock) {
			sold += number;
			stock -= number;
			result = true;
		}

		return result;
	}


	public Investor retrieveInvestorFromDatabase(String userName) {

		String dbName = userName + "_db";
		UserDbConnection userDb = new UserDbConnection(userName);
		return userDb.retrieveInvestorFromDatabase(dbName);

	}
/*record sold,purchased and the stock*/
	public boolean recordInvestorTransactionsToDb(Investor investor) {
		UserDbConnection userDb = new UserDbConnection(investor.getUser_name());
		return userDb.recordInvestorTransactionsToDb(investor);
	}

}
