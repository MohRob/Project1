package com.revature.projectOneFinal.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String name;
	private String username;
	private String password;
	private String role;
	public UserModel() {
		super();
	}
	public UserModel(int userId, String username, String password, String name, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	public boolean isPasswordMatch(String pass) {
		return password.equals(pass);
	}

	public boolean isRoleMatch(String role) {
		return role.equals(role);
	}

	
	

}
