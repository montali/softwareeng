package it.unipr.BottiMontali;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {

	protected String username;
	protected String password;

	public Person() {
		this.username = "";
		this.password = "";
	}
	public Person (String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean checkLogin (String password) {
		if (password == this.password){
			return true;
		}
		else {
			return false;
		}
	}
}
