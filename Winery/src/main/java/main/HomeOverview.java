package main;

/**
 *
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 *
 * @version     1.0
 * @since       1.0
 */

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.util.Pair;
import model.User;
import model.Wine;

import java.util.HashMap;

public class HomeOverview {

    private Main mainApp;
    private HashMap<Wine, Integer> ordersForWine;
    private ObservableList<Wine> top10Wines;

    @FXML
    private Text orderedWinesNo;

    @FXML
    private Text pendingOrdersNo;

    @FXML
    private Text newWinesNo;

    @FXML
    private TableView<Wine> top10Table;

    @FXML
    private TableColumn<Wine, Integer> noColumn;

    @FXML
    private TableColumn<Wine, String> nameColumn;

    @FXML
    private TableColumn<Wine, Integer> yearColumn;

    @FXML
    void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        noColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(this.ordersForWine.get(cellData.getValue())).asObject());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        orderedWinesNo.setText(Integer.toString(((User)this.mainApp.getLoggedIn()).getOrderedNo(this.mainApp.getShop())));
        pendingOrdersNo.setText(Integer.toString(((User)this.mainApp.getLoggedIn()).getPendingNo(this.mainApp.getShop())));
        newWinesNo.setText(Integer.toString(((User)this.mainApp.getLoggedIn()).getNewlyArrivedWines()));
        if (((User)this.mainApp.getLoggedIn()).getNewlyArrivedWines()>0){
            // The counter will be reset on the next login, so the user is reminded for the entire session.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New wine arrived!");
            alert.setHeaderText(null);
            alert.setContentText("One of the wines you requested has been added to the shop.");
            (this.mainApp.getShop()).resetNewlyArrivedWines((User)this.mainApp.getLoggedIn());
            alert.showAndWait();
        }
        this.updateTop10();
    }

    /**
     * This method updates the list of the top 10 wines in the catalog
     */

    private void updateTop10 (){
        Pair<ObservableList<Wine>, HashMap<Wine, Integer>> results = this.mainApp.getShop().getTopWines();
        this.top10Wines = results.getKey();
        this.ordersForWine = results.getValue();
        this.top10Table.setItems(this.top10Wines);
    }
}
