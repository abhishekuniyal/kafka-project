package com.abhi.kafka.model;

public class UserProfile {

	private String user;
	private String roles;

	public UserProfile(String user, String roles) {
		super();
		this.user = user;
		this.roles = roles;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
