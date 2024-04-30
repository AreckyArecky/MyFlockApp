/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myflock.myflockapp.view.financesview;

import com.myflock.myflockapp.entity.finances.CashIncomeEntry;
import com.myflock.myflockapp.entity.finances.MonthPicker;
import com.myflock.myflockapp.entity.finances.TransactionEntry;
import com.myflock.myflockapp.repo.TransactionEntryRepo;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DevelopmentMPOS
 */
public class FinancesViewController implements Initializable {

    @FXML
    private ChoiceBox monthPicker;
    @FXML
    private ChoiceBox yearPicker;

    @FXML
    private CheckBox cashIncomeSelect;

    @FXML
    private CheckBox cashOutcomeSelect;

    @FXML
    private CheckBox bankIncomeSelect;

    @FXML
    private CheckBox bankOutcomeSelect;

    @FXML
    private TilePane tileTables;

    @FXML
    private TableView cashIncomeTable;

    @FXML
    private TableView cashOutcomeTable;

    @FXML
    private TableView bankIncomeTable;

    @FXML
    private TableView bankOutcomeTable;

    @FXML
    private VBox cashIncome;

    @FXML
    private VBox cashOutcome;

    @FXML
    private VBox bankIncome;

    @FXML
    private VBox bankOutcome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tileTables.setPrefTileHeight(TextField.USE_COMPUTED_SIZE);
        tileTables.setPrefTileWidth(TextField.USE_COMPUTED_SIZE);
        tileTables.setMinWidth(TextField.USE_COMPUTED_SIZE);

        monthPicker.getItems().addAll(MonthPicker.values());
        monthPicker.getItems().add("YEARLY");
        int from = 1980;
        int to = 2050;
        List<Integer> years = new ArrayList<>();
        while (from <= to) {
            years.add(from++);
        }
        yearPicker.getItems().addAll(years);
        // TODO
    }

    public void show() {
        clearTables();
        hideSections();
        if (cashIncomeSelect.isSelected()) {
            showCashIncome();
        }
//        if (cashOutcomeSelect.isArmed()) {

//            showCashOutcome();
//        } else if (bankIncomeSelect.isArmed()) {
//            showBankIncome();
//        } else if (bankOutcomeSelect.isArmed()) {
//            showBankOutcome();
//        } else {
//            noSelectionPopup();
//        }
//
    }

    public void clearTables() {
        cashIncomeTable.getItems().clear();
        cashOutcomeTable.getItems().clear();
        bankIncomeTable.getItems().clear();
        bankOutcomeTable.getItems().clear();
    }

    public void hideSections() {
        cashIncome.setVisible(false);
        bankIncome.setVisible(false);
        cashOutcome.setVisible(false);
        bankOutcome.setVisible(false);
    }

    public void showCashIncome() {
        cashIncome.setVisible(true);
        createCashIncomeDataFromDate();
        buildTable(cashIncomeTable, createCashIncomeDataFromDate());

    }

    public ObservableList<CashIncomeEntry> createCashIncomeDataFromDate() {
        System.out.println(monthPicker.getValue());
        ObservableList<CashIncomeEntry> data = FXCollections.observableArrayList();
        TransactionEntryRepo repo = new TransactionEntryRepo();
        Collection<CashIncomeEntry> fromRepo;

        if (monthPicker.getValue() == null || monthPicker.getValue().equals("YEARLY")) {
            fromRepo = repo.findCashIncomeEntryByYear(Integer.parseInt(yearPicker.getValue().toString()));
        } else {
            fromRepo = repo.findCashIncomeEntryByYearAndMonth(Integer.parseInt(yearPicker.getValue().toString()), MonthPicker.getMonthNumberFromString(monthPicker.getValue().toString()));
        }
        if (!fromRepo.isEmpty()) {
            fromRepo.forEach(e -> data.add(new CashIncomeEntry(e.getDate(), e.getDescription(), e.getAmount())));
        }
        return data;
    }

    public void buildTable(TableView table, ObservableList data) {

        table.getColumns().clear();
        TableColumn date = new TableColumn("Date");
        TableColumn desc = new TableColumn("Description");
        TableColumn amnt = new TableColumn("Amount");

        date.setCellValueFactory(new PropertyValueFactory<TransactionEntry, String>("date"));
        desc.setCellValueFactory(new PropertyValueFactory<TransactionEntry, String>("description"));
        amnt.setCellValueFactory(new PropertyValueFactory<TransactionEntry, String>("amount"));

        table.getColumns().addAll(date, desc, amnt);

        table.setItems(data);
    }
}
