package main;

/**
 * This is the controller for the User Wine page where we can control all of the buttons and functionality
 *
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 *
 * @version     1.0
 * @since       1.0
 */

import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import model.Wine;


public class WinesPage {

    private Main mainApp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox<Integer> yearChoice;


    @FXML
    private Button resetButton;

    @FXML
    private Button requestButton;

    @FXML
    private TableView<Wine> wineTable;

    @FXML
    private TableColumn<Wine, Integer> yearColumn;

    @FXML
    private TableColumn<Wine, String> nameColumn;

    @FXML
    private TableColumn<Wine, Integer> qtyColumn;

    @FXML
    private TableColumn<Wine, String> vineColumn;

    @FXML
    private TableColumn<Wine, String> notesColumn;

    /**
     * This method shows to the user all of his wines
     */

    @FXML
    void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().qtyProperty().asObject());
        vineColumn.setCellValueFactory(cellData -> cellData.getValue().vineProperty());
        notesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());
        ObservableList<Integer> years = FXCollections.observableArrayList();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        int currentYear = Integer.parseInt(dtf.format(LocalDateTime.now()));
        for (int i = 1990; i <= currentYear; i++) {
            years.add(i);
        }
        yearChoice.setItems(years);
        requestButton.setOnMouseClicked(MouseEvent -> {
            String request = searchField.getText();
            ((User) this.mainApp.getLoggedIn()).requestWine(request, this.mainApp.getShop());
            searchField.setText("");
            yearChoice.setValue(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("You succesfully requested a "+request+"! Thank you.");
            alert.showAndWait();
        });
        resetButton.setOnMouseClicked(MouseEvent -> {
            searchField.setText("");
            yearChoice.setValue(null);
        });
        searchField.textProperty().addListener((observable, oldValue, newValue) -> this.updateSearch());
        yearChoice.valueProperty().addListener(ChangeListener -> this.updateSearch());
        wineTable.setOnMouseClicked( event -> {
            if( event.getClickCount() == 2 && wineTable.getSelectionModel().getSelectedItem() != null) {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("New order");
                dialog.setHeaderText("You're ordering " + wineTable.getSelectionModel().getSelectedItem().getName());
                dialog.setContentText("Please enter the quantity");
                TextField quantityEditor = dialog.getEditor();
                quantityEditor.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                        if (!newValue.matches("\\d*")) {
                            quantityEditor.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(quantity -> {
                    Integer quantityNo = Integer.parseInt(quantity);
                    if (quantityNo>wineTable.getSelectionModel().getSelectedItem().getQty()){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Order error!");
                        alert.setHeaderText("Too much wine ordered");
                        alert.setContentText("Please, check the wine quantity you ordered.");
                        alert.showAndWait();
                    } else {
                        ((User)this.mainApp.getLoggedIn()).orderWine(wineTable.getSelectionModel().getSelectedItem(), this.mainApp.getShop(), quantityNo);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText(null);
                        alert.setContentText("Your order is confirmed. Thank you!");
                        alert.showAndWait();
                    }
                });
            }});
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        updateSearch();
    }

    /**
     * This method is used to search a wines in te user wine's list
     */
    
    private void updateSearch() {
        if (!searchField.getText().equals("") && yearChoice.getValue() != null) {
            wineTable.setItems(this.mainApp.getShop().getWinebyNameandYear(searchField.getText(), yearChoice.getValue()));
        } else if (yearChoice.getValue() != null) {
            wineTable.setItems(((User)this.mainApp.getLoggedIn()).getWinesByYear(yearChoice.getValue(), this.mainApp.getShop()));
        } else {
            wineTable.setItems(((User)this.mainApp.getLoggedIn()).getWineByName(searchField.getText(), this.mainApp.getShop()));
        }
    }
}
