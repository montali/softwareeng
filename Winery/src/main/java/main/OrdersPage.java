package main;

/**
 * This is the controller for the User Orders page where we can controll all of the buttons and funcionality
 *
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 *
 * @version     1.0
 * @since       1.0
 */

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Order;
import model.User;

public class OrdersPage {

    private Main mainApp;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, String> wineColumn;

    @FXML
    private TableColumn<Order, Integer> yearColumn;

    @FXML
    private TableColumn<Order, Integer> qtyColumn;

    @FXML
    private TableColumn<Order, String> statusColumn;

    /**
     * This method shows all of the user's orders
     */

    @FXML
    void initialize() {
        wineColumn.setCellValueFactory(cellData -> cellData.getValue().getOrderedWine().nameProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().getOrderedWine().yearProperty().asObject());
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getOrderStatus());
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        ordersTable.setItems(((User)this.mainApp.getLoggedIn()).getMyOrders(this.mainApp.getShop()));
    }
}
