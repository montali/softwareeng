package main;

/**
 * This is the controller for the User homepage where we can control all of the buttons and functionality
 *
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 *
 * @version     1.0
 * @since       1.0
 */

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.User;
import model.Winehouse;

public class RootLayout {

    private User loggedIn;
    private Winehouse shop;

    @FXML
    private ImageView userPic;

    @FXML
    private Text usernameLabel;

    @FXML
    private HBox homeHBox;

    @FXML
    private Circle homeCircle;

    @FXML
    private HBox winesHBox;

    @FXML

    private Circle winesCircle;

    @FXML
    private HBox ordersHBox;

    @FXML
    private HBox logoutHBox;

    @FXML
    private Circle ordersCircle;

    private Main mainApp;

    public RootLayout(){}

    @FXML
    private void initialize() {
        ordersCircle.setVisible(false);
        winesCircle.setVisible(false);
        this.setMenuListeners();
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        usernameLabel.setText(this.mainApp.getLoggedIn().getUsername());
    }

    /**
     * This method shows the selected item and obscures the unselected
     *
     * @param selected
     */

    private void setSelectedMenuItem (String selected) {
        switch (selected){
            case "Home":
                homeCircle.setVisible(true);
                ordersCircle.setVisible(false);
                winesCircle.setVisible(false);
                break;
            case "Wines":
                homeCircle.setVisible(false);
                ordersCircle.setVisible(false);
                winesCircle.setVisible(true);
                break;
            case "Orders":
                homeCircle.setVisible(false);
                ordersCircle.setVisible(true);
                winesCircle.setVisible(false);
                break;
        }
    }

    /**
     * This method sets the event for the selected button. It calls the Main's method for every item.
     *
     */

    private void setMenuListeners(){
        this.winesHBox.setOnMouseClicked(clickEvent -> {
            this.mainApp.showWinesView();
            setSelectedMenuItem("Wines");
        });
        this.homeHBox.setOnMouseClicked(clickEvent -> {
            this.mainApp.showHomeOverview();
            setSelectedMenuItem("Home");
        });
        this.ordersHBox.setOnMouseClicked(clickEvent -> {
            this.mainApp.showOrdersView();
            setSelectedMenuItem("Orders");
        });
        this.logoutHBox.setOnMouseClicked(clickEvent -> this.mainApp.logOut());
    }
}
