package it.unipr.BottiMontali;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Person describes a generic user. It is the parent class of User and Seller.
 * In the future, plain saving of the password should be avoided.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */

public class Person {

	protected String username;
	protected String password;

	/**
	 * This constructor generates an empty person
	 * 
	 */
	public Person() {
		this.username = "";
		this.password = "";
	}
	/**
	 * This constructor generates a person from its username and password.
	 * 
	 * @param username
	 * @param password
	 */
	public Person (String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * This method returns the user's username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * This method sets the user's username.
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * This method gets the user's password.
	 * 
	 * @return the password.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * This method sets the user's password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method checks if the password is correct for the user.
	 * 
	 * @param password
	 * @return true if the login is ok
	 */
	public boolean checkLogin (String password) {
		if (password.equals(this.password)){
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This method generates a hashcode for the object.
	 *
	 * @return the hashcodes
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	/**
	 * This method checks if two Person objects are equal
	 * 
	 * @return whether the objects are equal
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	/**
	 * This method generates a string that describes the object.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[username=" + username + ", password=" + password + "]";
	}
	
	
}
