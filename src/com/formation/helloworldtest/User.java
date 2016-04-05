package com.formation.helloworldtest;

import java.io.Serializable;

public class User implements Serializable {

	private int photo;
	private String firstName;
	private String lastName;
	private boolean isActive;

	public User(String firstName, String lastName, int photo, boolean isActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
		this.isActive = isActive;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}
	
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return photo + firstName + lastName + isActive;
	}

}
