package model;

import java.util.ArrayList;

public class Node {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String regNumber;
	
	
	private Node leftNode ;
	private Node rightNode;
	
	
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getRegNumber() {
//		return regNumber;
//	}
//	public void setRegNumber(String regNumber) {
//		this.regNumber = regNumber;
//	}
//	
//	public Node getLeftNode() {
//		return leftNode;
//	}
//
//	public Node getRightNode() {
//		return rightNode;
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

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Node getLeftNode() {
		return leftNode;
	}
	
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}
	
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	

	@Override
	public String toString() {
		return "Node [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", regNumber=" + regNumber + ", leftNode=" + leftNode + ", rightNode=" + rightNode + "]";
	}
	
	//create node from distributors
	public Node createNode(String childRegNumber){
		
		Node node = new Node();
		
		ArrayList<Distributor> distributors = new ArrayList<Distributor>();
		Distributor distributor = new Distributor();
		
		
		distributors = distributor.getAllDistributors();
		
		
			
			for(Distributor distr : distributors){
				
				if(distr.getRegistrationNumber().equals(childRegNumber)){
					node.setFirstName(distr.getFirstName());
					node.setLastName(distr.getLastName());
					node.setPhoneNumber(distr.getPhoneNumber());
					node.setRegNumber(distr.getRegistrationNumber());					
//					node.setUserName(distr.getUserName());
//					node.setEmail(distr.getEmail());
//					node.setPhoneNumber(distr.getPhoneNumber());
//					node.setRegNumber(distr.getRegNumber());
					break;
				}
				
			}
			
		return node;
	}
	
//	public String toString(){
//		 String obj = "userName: " + this.userName +  "\nemail :  " + this.email + "\nphoneNumber : " + this.phoneNumber + 
//				 "\nregNumber :  " + this.regNumber  + "\nleftNode : " + this.leftNode +  "\nrightNode: " + this.rightNode + "\n\n";
//		 return obj;
//	}
}
