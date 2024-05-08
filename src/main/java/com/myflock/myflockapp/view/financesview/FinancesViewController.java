package com.myflock.myflockapp.view.financesview;

import com.myflock.myflockapp.entity.finances.BankIncomeEntry;
import com.myflock.myflockapp.entity.finances.BankOutcomeEntry;
import com.myflock.myflockapp.entity.finances.CashIncomeEntry;
import com.myflock.myflockapp.entity.finances.CashOutcomeEntry;
import com.myflock.myflockapp.entity.finances.MonthPicker;
import com.myflock.myflockapp.entity.finances.TransactionEntry;
import com.myflock.myflockapp.repo.TransactionEntryRepo;
import com.myflock.myflockapp.view.PopupBuilder;
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
import javafx.scene.layout.FlowPane;
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
    private FlowPane flowTables;

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
        monthPicker.getItems().addAll(MonthPicker.values());
        monthPicker.getItems().add("YEARLY");
        int from = 2000;
        int to = 2030;
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
        if (cashOutcomeSelect.isSelected()) {
            showCashOutcome();
        }
        if (bankIncomeSelect.isSelected()) {
            showBankIncome();
        }
        if (bankOutcomeSelect.isSelected()) {
            showBankOutcome();
        }
        if (noSelection()) {
            PopupBuilder.buildInfoPopup("Choose a table type.");
        }

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

    public boolean noSelection() {
        boolean noSelection = false;
        if (!cashIncomeSelect.isSelected() && !cashOutcomeSelect.isSelected() && !bankIncomeSelect.isSelected() && !bankOutcomeSelect.isSelected()) {
            noSelection = true;
        }
        return noSelection;
    }

    public void showCashIncome() {
        cashIncome.setVisible(true);
        buildTable(cashIncomeTable, createTableDataFromDate("CashIncome"));
    }

    public void showBankIncome() {
        bankIncome.setVisible(true);
        buildTable(bankIncomeTable, createTableDataFromDate("BankIncome"));
    }

    public void showCashOutcome() {
        cashOutcome.setVisible(true);
        buildTable(cashOutcomeTable, createTableDataFromDate("CashOutcome"));
    }

    public void showBankOutcome() {
        bankOutcome.setVisible(true);
        buildTable(bankOutcomeTable, createTableDataFromDate("BankOutcome"));
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
        table.setPrefWidth(0.45 * flowTables.getWidth());
        desc.setPrefWidth(0.65 * table.getWidth());
        date.setPrefWidth(0.2 * table.getWidth());
        amnt.setPrefWidth(0.1 * table.getWidth());

    }

    public ObservableList<TransactionEntry> createTableDataFromDate(String type) {

        ObservableList<TransactionEntry> data = FXCollections.observableArrayList();

        switch (type) {
            case "CashIncome":
                createCashIncomeDataByDate(data);
                break;
            case "BankIncome":
                createBankIncomeDataByDate(data);
                break;
            case "CashOutcome":
                createCashOutcomeDataByDate(data);
                break;
            case "BankOutcome":
                createBankOutcomeDataByDate(data);
        }

        return data;
    }

    public ObservableList<CashIncomeEntry> createCashIncomeDataByDate(ObservableList data) {

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

    public ObservableList<BankIncomeEntry> createBankIncomeDataByDate(ObservableList data) {

        TransactionEntryRepo repo = new TransactionEntryRepo();
        Collection<BankIncomeEntry> fromRepo;

        if (monthPicker.getValue() == null || monthPicker.getValue().equals("YEARLY")) {
            fromRepo = repo.findBankIncomeEntryByYear(Integer.parseInt(yearPicker.getValue().toString()));
        } else {
            fromRepo = repo.findBankIncomeEntryByYearAndMonth(Integer.parseInt(yearPicker.getValue().toString()), MonthPicker.getMonthNumberFromString(monthPicker.getValue().toString()));
        }
        if (!fromRepo.isEmpty()) {
            fromRepo.forEach(e -> data.add(new BankIncomeEntry(e.getDate(), e.getDescription(), e.getAmount())));
        }
        return data;
    }

    public ObservableList<CashOutcomeEntry> createCashOutcomeDataByDate(ObservableList data) {

        TransactionEntryRepo repo = new TransactionEntryRepo();
        Collection<CashOutcomeEntry> fromRepo;

        if (monthPicker.getValue() == null || monthPicker.getValue().equals("YEARLY")) {
            fromRepo = repo.findCashOutcomeEntryByYear(Integer.parseInt(yearPicker.getValue().toString()));
        } else {
            fromRepo = repo.findCashOutcomeEntryByYearAndMonth(Integer.parseInt(yearPicker.getValue().toString()), MonthPicker.getMonthNumberFromString(monthPicker.getValue().toString()));
        }
        if (!fromRepo.isEmpty()) {
            fromRepo.forEach(e -> data.add(new CashOutcomeEntry(e.getDate(), e.getDescription(), e.getAmount())));
        }
        return data;
    }

    public ObservableList<BankOutcomeEntry> createBankOutcomeDataByDate(ObservableList data) {

        TransactionEntryRepo repo = new TransactionEntryRepo();
        Collection<BankOutcomeEntry> fromRepo;

        if (monthPicker.getValue() == null || monthPicker.getValue().equals("YEARLY")) {
            fromRepo = repo.findBankOutcomeEntryByYear(Integer.parseInt(yearPicker.getValue().toString()));
        } else {
            fromRepo = repo.findBankOutcomeEntryByYearAndMonth(Integer.parseInt(yearPicker.getValue().toString()), MonthPicker.getMonthNumberFromString(monthPicker.getValue().toString()));
        }
        if (!fromRepo.isEmpty()) {
            fromRepo.forEach(e -> data.add(new BankOutcomeEntry(e.getDate(), e.getDescription(), e.getAmount())));
        }
        return data;
    }

}
