package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

/**
 * Winehouse describes a winehouse and its properties.
 *
 * SQL DB DATA:
 * SQL_USR: mont_wineryuser
 * SQL_DB: mont_winery
 * SQL_PWD: sweng2020
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Winehouse {
	private static final String DBURL = "jdbc:mysql://simmy.chickenkiller.com:3360/Winery?";
	private static final String ARGS = "serverTimezone=UTC";
	private static final String LOGIN = "winery_user";
	private static final String PASSWORD = "sweng2020";
	
	/**
	 * This constructor generates an empty winehouse
	 */
	public Winehouse() {
	}


	/**
	 * This method gets the winehouse's wines
	 * 
	 * @return the wines
	 */
	public ObservableList<Wine> getWines(){
		ObservableList<Wine> allWines = FXCollections.observableArrayList();
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);Statement stmt = conn.createStatement()){
			String strSelect = "SELECT * FROM Wine";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()){
				allWines.add(new Wine(rset.getString("NAME"), rset.getString("NOTES"), rset.getString("VINE"), rset.getInt("QTY"), rset.getInt("YEAR")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return allWines;
	}

	/**
	 * This method gets the winehouse's orders
	 *
	 * @return the orders
	 */
	public ObservableList<Order> getOrders(){
		ObservableList<Order> allOrders = FXCollections.observableArrayList();
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);Statement stmt = conn.createStatement()){
			String strSelect = "SELECT * FROM Orders, User, Wine WHERE Orders.USER_ID=User.ID AND Wine.ID=Orders.WINE_ID";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()){
				allOrders.add(new Order(new User(rset.getString("User.USERNAME"), rset.getString("User.PASSWORD"), rset.getInt("User.NEWLY_ARRIVED")), new Wine(rset.getString("Wine.NAME"),rset.getString("NOTES"), rset.getString("VINE"), rset.getInt("QTY"), rset.getInt("YEAR")), rset.getInt("QUANTITY"), rset.getBoolean("SHIPPED")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return allOrders;
	}
	
	/**
	 * This method gets the winehouse's sellers
	 * 
	 * @return the sellers
	 */
	public ObservableList<Seller> getSellers(){
		ObservableList<Seller> allSellers = FXCollections.observableArrayList();
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);Statement stmt = conn.createStatement()){
			String strSelect = "SELECT * FROM User WHERE ISADMIN=1";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()){
				allSellers.add(new Seller(rset.getString("USERNAME"), rset.getString("PASSWORD")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return allSellers;
	}

	/**
	 * This method gets the winehouse's members
	 * 
	 * @return the members
	 */
	public ObservableList<User> getMembers(){
		ObservableList<User> allMembers = FXCollections.observableArrayList();
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);Statement stmt = conn.createStatement()){
			String strSelect = "SELECT * FROM User WHERE ISADMIN=0";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()){
				allMembers.add(new User(rset.getString("USERNAME"), rset.getString("PASSWORD")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return allMembers;
	}

	/**
	 * This method gets the winehouse's requested wines
	 * 
	 * @return the requests
	 */
	public ObservableList<Request> getRequestedWines(){
		ObservableList<Request> allRequests = FXCollections.observableArrayList();
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);Statement stmt = conn.createStatement()){
			String strSelect = "SELECT * FROM Request, User WHERE Request.USER_ID=User.ID";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()){
				allRequests.add(new Request(new User(rset.getString("User.USERNAME"), rset.getString("User.PASSWORD"), rset.getInt("User.NEWLY_ARRIVED")), rset.getString("REQUEST")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return allRequests;
	}

	
	/**
	 * This method logs a person in the winehouse
	 * 
	 * @param username
	 * @param password
	 * @return the person object
	 */
	public Person login (String username, String password){
		String strSelect = "SELECT * FROM User WHERE USERNAME=? AND PASSWORD=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD)){
			PreparedStatement stmt = conn.prepareStatement(strSelect);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()){
				if (rset.getBoolean("ISADMIN"))
					return new Seller(rset.getString("USERNAME"), rset.getString("PASSWORD"));
				else
					return new User(rset.getString("USERNAME"), rset.getString("PASSWORD"), rset.getInt("NEWLY_ARRIVED"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	/**
	 * This method registers a new user to the winehouse
	 * 
	 * @param username
	 * @param password
	 */
	public boolean signUp (String username, String password) {
		String strSelect = "SELECT * FROM User WHERE USERNAME=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strSelect)){
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next())
				return false;
			String insertQuery = "INSERT INTO User(USERNAME, PASSWORD, ISADMIN, NEWLY_ARRIVED) VALUES (?,?,?,?)";
			PreparedStatement updateStmt = conn.prepareStatement(insertQuery);
			updateStmt.setString(1, username);
			updateStmt.setString(2,password);
			updateStmt.setInt(3, 0);
			updateStmt.setInt(4, 0);
			updateStmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return true;
	}

	/**
	 * This method looks for wines in the DB by their name
	 * 
	 * @param toSearch
	 * @return the results
	 */
	public ObservableList<Wine> findWinesName(String toSearch){
		if (toSearch.equals(""))
			return this.getWines();
		ObservableList<Wine> searched = FXCollections.observableArrayList();
		String strSelect = "SELECT * FROM Wine WHERE NAME LIKE ?";

		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strSelect)){
			stmt.setString(1, "%"+ toSearch+"%");
			ResultSet rset = stmt.executeQuery();
			while (rset.next()){
				searched.add(new Wine(rset.getString("NAME"), rset.getString("NOTES"), rset.getString("VINE"), rset.getInt("QTY"), rset.getInt("YEAR")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return searched;
	}

	public ObservableList<Order> getPendingOrders (){
		ObservableList<Order> searched = FXCollections.observableArrayList();
		for (Order temp: this.getOrders()){
			if (!temp.isShipped())
				searched.add(temp);
		}
		return searched;
	}

	public Integer getPendingOrdersNo (){
		Integer i = 0;
		for (Order temp: this.getOrders()){
			if (!temp.isShipped())
				i++;
		}
		return i;
	}

	public ObservableList<Wine> getWinebyNameandYear (String name, int year) {
		ObservableList<Wine> searched = FXCollections.observableArrayList();
		String strSelect = "SELECT * FROM Wine WHERE NAME LIKE ? AND YEAR=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strSelect)){
			stmt.setString(1, name);
			stmt.setInt(2, year);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()){
				searched.add(new Wine(rset.getString("NAME"), rset.getString("NOTES"), rset.getString("VINE"), rset.getInt("QTY"), rset.getInt("YEAR")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return searched;
	}
	/**
	 * This method looks for wines in the DB by their year
	 * 
	 * @param toSearch
	 * @return the results
	 */
	public ObservableList<Wine> findWinesYear(Integer toSearch){
		ObservableList<Wine> searched = FXCollections.observableArrayList();
		String strSelect = "SELECT * FROM Wine WHERE YEAR=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strSelect)){
			stmt.setInt(1, toSearch);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()){
				searched.add(new Wine(rset.getString("NAME"), rset.getString("NOTES"), rset.getString("VINE"), rset.getInt("QTY"), rset.getInt("YEAR")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return searched;
	}

	private int getWineID (String name, int year) {
		int resultID = 0;
		String strSelect = "SELECT * FROM Wine WHERE NAME=? AND YEAR=?";

		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strSelect)){
			stmt.setString(1, name);
			stmt.setInt(2, year);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()){
				resultID = rset.getInt("ID");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return resultID;
	}

	public void editWineQty(Wine toEdit, Integer quantity) {
		String updateQuery = "UPDATE Wine SET QTY = ? WHERE NAME=? AND YEAR=? AND VINE=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
			stmt.setInt(1, quantity);
			stmt.setString(2, toEdit.getName());
			stmt.setInt(3, toEdit.getYear());
			stmt.setString(4, toEdit.getVine());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method adds multiple wines to the DB
	 * 
	 * @param toAdd
	 * @param quantity
	 */
	public void addWine(Wine toAdd, Integer quantity) {
		String strSelect = "SELECT * FROM Wine WHERE NAME=? AND YEAR=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strSelect)){
			stmt.setString(1, toAdd.getName());
			stmt.setInt(2, toAdd.getYear());
			ResultSet rset = stmt.executeQuery();
			if (rset.next()){
				PreparedStatement updateStmt = conn.prepareStatement("UPDATE Wine SET QTY=? WHERE ID=?");
				updateStmt.setInt(1, (rset.getInt("QTY")+quantity));
				updateStmt.setInt(2, rset.getInt("ID"));
				updateStmt.executeUpdate();
			} else {
				String insertWine = "INSERT INTO Wine(NAME, NOTES, VINE, QTY, YEAR) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement insertStmt = conn.prepareStatement(insertWine);
				insertStmt.setString(1, toAdd.getName());
				insertStmt.setString(2, toAdd.getNotes());
				insertStmt.setString(3, toAdd.getVine());
				insertStmt.setInt(4, quantity);
				insertStmt.setInt(5, toAdd.getYear());
				insertStmt.executeUpdate();
				insertStmt.closeOnCompletion();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		String update = "UPDATE User,Request SET NEWLY_ARRIVED = NEWLY_ARRIVED + 1 WHERE User.ID=Request.USER_ID AND Request.REQUEST=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(update)){
			stmt.setString(1, toAdd.getName());
			stmt.executeUpdate();
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	/**requestedWine
	 * This method removes a single wine
	 * 
	 * @param authorizer
	 * @param toRemove
	 * @return
	 */
	public boolean removeWine(Seller authorizer, Wine toRemove, Integer quantity) {
		//guardo se authorizer è un seller
		if(!this.getSellers().contains(authorizer)) {
			return false;
		}
		Wine searched = this.getWinebyNameandYear(toRemove.getName(), toRemove.getYear()).get(0);
		if(searched != null) {
			if (searched.getQty()-quantity <0)
				return false;
			String strUpdate = "UPDATE Wine SET QTY=QTY - ? WHERE NAME=? AND YEAR=?";
			try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strUpdate)){
				stmt.setInt(1, quantity);
				stmt.setString(2, toRemove.getName());
				stmt.setInt(3, toRemove.getYear());
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			return true;
		}
		return false;
	}

	private int getUserID (User toGet) {
		String userQuery = "SELECT ID FROM User WHERE USERNAME=?";

		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(userQuery)){
			stmt.setString(1, toGet.getUsername());
			ResultSet userRes = stmt.executeQuery();
			int userId = 0;
			while(userRes.next()){
				userId = userRes.getInt("ID");
			}
			return userId;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return 0;
	}
	/**
	 * This method adds a wine to the requests
	 * 
	 * @param requester
	 * @param toRequest
	 */
	public void requestWine(User requester, String toRequest) {
		String strInsert = "INSERT INTO Request(USER_ID,REQUEST) VALUES (?,?)";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strInsert)){
			stmt.setInt(1, getUserID(requester));
			stmt.setString(2, toRequest);
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public Pair<ObservableList<Wine>, HashMap<Wine, Integer>> getTopWines () {
		ObservableList<Wine> searched = FXCollections.observableArrayList();
		HashMap<Wine, Integer> ordersForWine = new HashMap<>();

		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);Statement stmt = conn.createStatement()){
			String strInsert = "SELECT Wine.*, SUM(Orders.QUANTITY) AS ORDINI FROM Orders,Wine WHERE Wine.ID = Orders.WINE_ID GROUP BY Wine.NAME ORDER BY ORDINI DESC";
			ResultSet rset = stmt.executeQuery(strInsert);
			while (rset.next()){
				Wine temp = new Wine(rset.getString("Wine.NAME"),rset.getString("Wine.NOTES"), rset.getString("Wine.VINE"), rset.getInt("Wine.QTY"),rset.getInt("Wine.YEAR"));
				searched.add(temp);
				int currentOrders = ordersForWine.containsKey(temp) ? ordersForWine.get(temp) : 0;
				ordersForWine.put(temp, currentOrders+rset.getInt("ORDINI"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return new Pair<>(searched, ordersForWine);
	}

	public void deleteWine(Wine toDelete) {
		String strDelete = "DELETE FROM Wine WHERE NAME=? AND YEAR=? AND VINE=?";

		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strDelete)){
			stmt.setString(1, toDelete.getName());
			stmt.setInt(2, toDelete.getYear());
			stmt.setString(3, toDelete.getVine());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	/**
	 * This method orders a new wine
	 * 
	 * @param orderer
	 * @param wantedWine
	 * @param quantity
	 */
	public void orderWine (User orderer, Wine wantedWine, Integer quantity){
		String insertQuery = "INSERT INTO Orders(USER_ID, WINE_ID, QUANTITY) VALUES (?,?,?)";

		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(insertQuery)){
			stmt.setInt(1, getUserID(orderer));
			stmt.setInt(2, getWineID(wantedWine.getName(), wantedWine.getYear()));
			stmt.setInt(3, quantity);
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * This method ships the ordered wines
	 * 
	 */
	public void shipWines (Seller authorizer) {
		for (Order pending: this.getPendingOrders()){
			this.removeWine(authorizer, pending.getOrderedWine(), pending.getQuantity());
		}
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);Statement stmt = conn.createStatement()){
			String insertQuery = "UPDATE Orders SET SHIPPED=1";
			stmt.executeUpdate(insertQuery);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	

	/**
	 * This method returns the orders a user has made
	 * 
	 * @param requester
	 * @return the orders
	 */
	public ObservableList<Order> getOrdersForUser (User requester) {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		String strSelect = "SELECT * FROM Orders, Wine WHERE Orders.WINE_ID=Wine.ID AND USER_ID=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strSelect)){
			stmt.setInt(1, getUserID(requester));
			ResultSet rset = stmt.executeQuery();
			while (rset.next()){
				orders.add(new Order(requester, new Wine(rset.getString("Wine.NAME"), rset.getString("Wine.NOTES"), rset.getString("Wine.VINE"), rset.getInt("QTY"), rset.getInt("YEAR")), rset.getInt("QUANTITY"), rset.getBoolean("SHIPPED")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return orders;
	}

	public void resetNewlyArrivedWines (User requester) {
		String strUpdate = "UPDATE User SET NEWLY_ARRIVED=0 WHERE USERNAME=?";
		try (Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);PreparedStatement stmt = conn.prepareStatement(strUpdate)){
			stmt.setString(1, requester.getUsername());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
