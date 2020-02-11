package main;

/**
 *This is the controller for the Admin's homepage where we can control all of the buttons and functionality.
 *
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 *
 * @version     1.0
 * @since       1.0
 */

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class AdminRootLayout {

    private Main mainApp;

    @FXML
    private Text usernameLabel;

    @FXML
    private HBox homeHBox;

    @FXML
    private Circle homeCircle;

    @FXML
    private HBox addWineHBox;

    @FXML
    private Circle addWineCircle;

    @FXML
    private HBox editWineHBox;

    @FXML
    private Circle editWineCircle;

    @FXML
    private HBox logoutHBox;


    @FXML
    private void initialize() {


    }

    /**
     * This method shows the selected item from the menu and obscures the unselected items
     *
     * @param selected
     */

    private void setSelectedMenuItem (String selected) {
        switch (selected){
            case "Home":
                homeCircle.setVisible(true);
                addWineCircle.setVisible(false);
                editWineCircle.setVisible(false);

                break;
            case "Add":
                homeCircle.setVisible(false);
                addWineCircle.setVisible(true);
                editWineCircle.setVisible(false);

                break;
            case "Edit":
                editWineCircle.setVisible(true);
                homeCircle.setVisible(false);
                addWineCircle.setVisible(false);

        }
    }

    /**
     * This method sets the events for the selected item. For every item
     * it calls the respective Main's method
     */

    private void setMenuListeners(){
        this.homeHBox.setOnMouseClicked(clickEvent -> {
            this.mainApp.showHomeOverview();
            setSelectedMenuItem("Home");
        });
        this.addWineHBox.setOnMouseClicked(clickEvent -> {
            this.mainApp.showAdminAddWine();
            setSelectedMenuItem("Add");
        });
        this.editWineHBox.setOnMouseClicked(clickEvent -> {
            this.mainApp.showAdminWineList();
            setSelectedMenuItem("Edit");
        });
        this.logoutHBox.setOnMouseClicked(clickEvent -> this.mainApp.logOut());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        this.usernameLabel.setText(this.mainApp.getLoggedIn().getUsername());
        setSelectedMenuItem("Home");
        setMenuListeners();
    }
}
