package model;

import java.time.LocalDate;

import database.UserDbConnection;

public abstract  class User {

	private int id = 0; // link the users_db with the userName_db
	private String user_name;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private String password;
	private String date;
	
	User(){
		this.date = LocalDate.now().toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(){
		UserDbConnection dbConnection = new UserDbConnection("users_db");
		this.id = dbConnection.setId();
	}
	public  void retrieveId(int id) {
		this.id = id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate() {
		this.date = LocalDate.now().toString();
	}

	public String login(String userName,String password){
		UserDbConnection userDb = new UserDbConnection("users");
		return userDb.login(userName, password);
	}
	
	public abstract String toString();

	}
