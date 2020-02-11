package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 *
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 *
 * @version     1.0
 * @since       1.0
 */

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    private Person loggedIn;
    private Winehouse shop;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.shop = new Winehouse();
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Winery");

        initLoginLayout();

        primaryStage.show();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            RootLayout controller = loader.getController();
            controller.setMainApp(this);
            showHomeOverview();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initAdminLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../AdminRootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            AdminRootLayout controller = loader.getController();
            controller.setMainApp(this);
            showHomeOverview();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initLoginLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../LoginView.fxml"));

            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void logOut() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../LoginView.fxml"));

            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
            LoginController controller = loader.getController();
            controller.setMainApp(this);
            this.loggedIn = null;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showHomeOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            if (loggedIn instanceof Seller)
                loader.setLocation(getClass().getResource("../AdminHomeOverview.fxml"));
            else
                loader.setLocation(getClass().getResource("../HomeOverview.fxml"));
            AnchorPane homeOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(homeOverview);
            if (loggedIn instanceof Seller) {
                AdminHomeOverview controller = loader.getController();
                controller.setMainApp(this);
            } else {
                HomeOverview controller = loader.getController();
                controller.setMainApp(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showAdminAddWine () {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../AdminAddWine.fxml"));
            AnchorPane winesOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(winesOverview);
            AdminAddWine controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdminWineList () {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../AdminListWines.fxml"));
            AnchorPane winesOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(winesOverview);
            AdminListWines controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showWinesView () {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../WinesPage.fxml"));
            AnchorPane winesOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(winesOverview);
            WinesPage controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOrdersView () {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../OrdersPage.fxml"));
            AnchorPane winesOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(winesOverview);
            OrdersPage controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    public Person getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Person loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Winehouse getShop() {
        return shop;
    }

    public void setShop(Winehouse shop) {
        this.shop = shop;
    }
}
