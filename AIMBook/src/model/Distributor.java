package model;

import java.util.ArrayList;

import database.DbConnection;

public class Distributor {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String registrationNumber;
	private String dbName;
	private String password;

	private String uplineregistrationNumber;
	private String uplineSide;
	private String rightChild;
	private String leftChild;
	
	
	
//	public void setpassword(String password) {
//		this.password = password;
//	}
//	public String getpassword() {
//		return this.password;
//	}
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//	public String getPhoneNumber() {
//		return this.phoneNumber;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getEmail() {
//		return this.email;
//	}
//	public void setregistrationNumber(String registrationNumber) {
//		this.registrationNumber = registrationNumber;
//	}
//	
//	public String getregistrationNumber() {
//		return this.registrationNumber;
//	}
//	
//	public String getUpline() {
//		return this.upline;
//	}
//	
//	public void setUplineSide(String uplineSide) {
//		this.uplineSide = uplineSide;
//	}
//	public String getUplineSide() {
//		return this.uplineSide;
//	}
//	
//	public void setRightChild(String rightChild) {
//		this.rightChild = rightChild;
//	}
//	public String getRightChild() {
//		return this.rightChild;
//	}
//	public void setLeftChild(String leftChild) {
//		this.leftChild = leftChild;
//	}
//	public String getLeftChild() {
//		return this.leftChild;
//	}
//	
//	public String toString() {
//		return this.password + this.phoneNumber + this.email + this.registrationNumber 
//				+ this.upline + this.uplineSide + this.rightChild + this.leftChild;
//	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUplineregistrationNumber() {
		return uplineregistrationNumber;
	}

	public void setUplineregistrationNumber(String uplineregistrationNumber) {
		this.uplineregistrationNumber = uplineregistrationNumber;
	}

	public String getUplineSide() {
		return uplineSide;
	}

	public void setUplineSide(String uplineSide) {
		this.uplineSide = uplineSide;
	}

	public String getRightChild() {
		return rightChild;
	}

	public void setRightChild(String rightChild) {
		this.rightChild = rightChild;
	}

	public String getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(String leftChild) {
		this.leftChild = leftChild;
	}
	

	
	@Override
	public String toString() {
		return "Distributor [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", registrationNumber=" + registrationNumber + ", dbName=" + dbName + ", password=" + password + ", uplineregistrationNumber="
				+ uplineregistrationNumber + ", uplineSide=" + uplineSide + ", rightChild=" + rightChild + ", leftChild="
				+ leftChild + "]";
	}
	
	/*First distributor  has default upline (uplineRegNumber = 0)
	 * */
	public boolean registerDistributor(Distributor distributor) {
		DbConnection db = new  DbConnection(dbName);
		boolean registrationSuccess = false;
		boolean updateUplineSuccess = false;
		boolean success = false;
		
		registrationSuccess = db.registerDistributor(distributor);
		if(registrationSuccess){
			updateUplineSuccess = updateUpline(distributor.getUplineregistrationNumber(),
					distributor.getRegistrationNumber(),distributor.getUplineSide());
			if(updateUplineSuccess == true ){
				success = true;
			}
			else if (distributor.getUplineregistrationNumber().equals("0")){
				success = true;
			}
			
		}
		
		else if (!success){
			if(registrationSuccess == true){
				//delete the distributor's registered details from the database
				distributor.removeDistributor(distributor.getRegistrationNumber());
				System.out.println("distributor removed");
			}
			success = false;
		}
		return success;
			
	} 
	
	public boolean login(String userName, String password){
		DbConnection db =new DbConnection(dbName);
		
		return db.login(userName,password);
	}
	
	
	public boolean changePassword(String registrationNumber,String oldpassword,String newpassword){
		DbConnection db = new DbConnection(dbName);
		return db.changePassword(registrationNumber,oldpassword,newpassword);
		
}
	public boolean removeDistributor(String registrationNumber) {
		DbConnection db = new DbConnection(dbName);
		return db.removeDistributor(registrationNumber);
	}
//	public void setUpline(String registrationNumber) {
//		this.upline = registrationNumber;
//	}
	
	
	
	
	
	public boolean updateUpline(String uplineregistrationNumber,String childregistrationNumber,String childSide) {
		DbConnection db = new DbConnection(dbName);
		return db.updateUpline(uplineregistrationNumber, childregistrationNumber, childSide);
	}
	
	public ArrayList<Distributor> getAllDistributors(){
		DbConnection db = new DbConnection(dbName);
		return db.getAllDistributors();
	}
	
	public Distributor getDistributor(String distributorregistrationNumber){
		Distributor distributor = new Distributor();
		
		ArrayList <Distributor> distributors = new ArrayList<Distributor>();
		
		distributors = distributor.getAllDistributors();
		for(Distributor distr : distributors){
			if(distributorregistrationNumber.equals(distr.getRegistrationNumber())){
				distributor = distr;
				break;
			}
		}
		return distributor;
		
	}
	public String getregistrationNumber(String userName,String password){
		DbConnection db = new DbConnection(dbName);
		
		return db.getregistrationNumber(userName,password);
	}
}
