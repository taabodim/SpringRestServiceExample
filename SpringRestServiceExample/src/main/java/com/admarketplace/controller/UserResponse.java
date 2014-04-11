package com.admarketplace.controller;

import java.util.List;

import com.admarketplace.controller.User;

public class UserResponse {
	String user;
	String msg;
	User userObj;
	List<User> allUsers;
	
	UserResponse(String myUser, String message) {
		user = myUser;
		msg = message;
	}

	UserResponse(User obj) {
		userObj = obj;
	}
	UserResponse(List<User> allUsersObj) {
		allUsers = allUsersObj;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	
}
