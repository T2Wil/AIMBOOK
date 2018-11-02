package tests;

import database.UserDbConnection;

public class Main {

	public static void main(String[] args) {
		UserDbConnection uDb = new UserDbConnection();
		System.out.println(uDb.getPurchaseTransactionIds("54681627", "coffe"));
		
	}

}
