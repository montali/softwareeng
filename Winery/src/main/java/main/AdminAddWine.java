package main;

/**
 * This is the controller for the AddWine page where we can control all of the buttons and functionality.
 *
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 *
 * @version     1.0
 * @since       1.0
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Request;
import model.Wine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminAddWine {

    private Main mainApp;

    @FXML
    private ChoiceBox<String> requestChooser;

    @FXML
    private TextField nameField;

    @FXML
    private TextField vineField;

    @FXML
    private TextField notesField;

    @FXML
    private ChoiceBox<Integer> yearChooser;

    @FXML
    private TextField qtySelector;

    @FXML
    private Button addButton;

    /**
     * This method initializes the fields for adding a new wine and sets the event for the Add button
     */

    @FXML
    private void initialize() {
        requestChooser.valueProperty().addListener(ChangeListener -> {
            nameField.setText(requestChooser.getValue());
        });
        ObservableList<Integer> years = FXCollections.observableArrayList();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        int currentYear = Integer.parseInt(dtf.format(LocalDateTime.now()));
        for (int i = 1990; i <= currentYear; i++) {
            years.add(i);
        }
        yearChooser.setItems(years);
        addButton.setOnMouseClicked(clickEvent -> {
            this.addWine();
        });
        qtySelector.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    qtySelector.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        ObservableList<String> requests = FXCollections.observableArrayList();
        for (Request requested : this.mainApp.getShop().getRequestedWines()) {
            requests.add(requested.getWineName());
        }
        requestChooser.setItems(requests);
    }

    /**
     * This method is used to add a new wine on the list. If the data are not correct it sends an alert message
     * Else it calls the Main's method to add a new wine.
     * Finally it shows an "ok" message
     */

    private void addWine() {
        if (yearChooser.getValue() == null || qtySelector.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Insert data!");
            alert.setHeaderText(null);
            alert.setContentText("Please, insert all the requested data.");
            alert.showAndWait();
            return;
        }
        this.mainApp.getShop().addWine(new Wine(nameField.getText(), notesField.getText(), vineField.getText(), Integer.parseInt(qtySelector.getText()), yearChooser.getValue()), Integer.parseInt(qtySelector.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("Wine added.");
        alert.showAndWait();
        requestChooser.setValue(null);
        nameField.setText("");
        vineField.setText("");
        notesField.setText("");
        yearChooser.setValue(null);
        qtySelector.setText("");
    }
}
