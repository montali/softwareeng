package main;

/**
 * This is the controller for the Admin's Edit page where we can control all the buttons and functionality
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
import javafx.scene.control.*;
import model.Seller;
import model.User;
import model.Wine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AdminListWines {

    private Main mainApp;

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox<Integer> yearChoice;

    @FXML
    private Button resetButton;

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
     * This method shows all the wines and allows admin to change wines
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
        resetButton.setOnMouseClicked(MouseEvent -> {
            searchField.setText("");
            yearChoice.setValue(null);
        });
        searchField.textProperty().addListener((observable, oldValue, newValue) -> this.updateSearch());
        yearChoice.valueProperty().addListener(ChangeListener -> this.updateSearch());
        wineTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && wineTable.getSelectionModel().getSelectedItem() != null) {
                TextInputDialog dialog = new TextInputDialog(Integer.toString(wineTable.getSelectionModel().getSelectedItem().getQty()));
                dialog.setTitle("Edit quantity");
                dialog.setHeaderText("You're editing " + wineTable.getSelectionModel().getSelectedItem().getName());
                dialog.setContentText("Please enter the new quantity or 0 to delete wine.");
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
                    String message = "";
                        if (quantityNo == 0){
                            this.mainApp.getShop().deleteWine(wineTable.getSelectionModel().getSelectedItem());
                            message = "Wine deleted";
                        } else {
                            message = "Quantity edited.";
                            this.mainApp.getShop().editWineQty(wineTable.getSelectionModel().getSelectedItem(), quantityNo);
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText(null);
                        alert.setContentText(message);
                        alert.showAndWait();
                        updateSearch();
                });
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
        wineTable.setItems(this.mainApp.getShop().getWines());
        updateSearch();
    }

    /**
     * This method searches wines in the wines list
     */

    private void updateSearch() {
        if (!searchField.getText().equals("") && yearChoice.getValue() != null) {
            wineTable.setItems(this.mainApp.getShop().getWinebyNameandYear(searchField.getText(), yearChoice.getValue()));
        } else if (yearChoice.getValue() != null) {
            wineTable.setItems(this.mainApp.getShop().findWinesYear(yearChoice.getValue()));
        } else {
            wineTable.setItems(this.mainApp.getShop().findWinesName(searchField.getText()));
        }
    }
}
