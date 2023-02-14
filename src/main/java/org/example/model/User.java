package org.example.model;

import java.util.Objects;

public class User {

	private final String login;
	private String password;

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLogin(), getPassword());
	}

	@Override
	public String toString() {
		return "User{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
