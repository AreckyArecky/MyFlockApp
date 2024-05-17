package com.myflock.myflockapp.view.financesview;

import com.myflock.myflockapp.entity.finances.BankIncomeEntry;
import com.myflock.myflockapp.entity.finances.BankOutcomeEntry;
import com.myflock.myflockapp.entity.finances.CashIncomeEntry;
import com.myflock.myflockapp.entity.finances.CashOutcomeEntry;
import com.myflock.myflockapp.entity.finances.MonthPicker;
import com.myflock.myflockapp.entity.finances.TransactionEntry;
import com.myflock.myflockapp.entity.finances.TransactionType;
import static com.myflock.myflockapp.entity.finances.TransactionType.BANK_INCOME;
import com.myflock.myflockapp.repo.TransactionEntryRepo;
import com.myflock.myflockapp.view.popup.PopupBuilder;
import com.myflock.myflockapp.view.popup.TransactionPopupBuilder;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

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

    @FXML
    private Button addCashIncome;

    @FXML
    private Button addCashOutcome;

    @FXML
    private Button addBankIncome;

    @FXML
    private Button addBankOutcome;

    @FXML
    private Button deleteCashIncome;

    @FXML
    private Button deleteCashOutcome;

    @FXML
    private Button deleteBankIncome;

    @FXML
    private Button deleteBankOutcome;

    @FXML
    private Button editCashIncome;

    @FXML
    private Button editCashOutcome;

    @FXML
    private Button editBankIncome;

    @FXML
    private Button editBankOutcome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideSections();
        monthPicker.getItems().addAll(MonthPicker.values());
        monthPicker.getItems().add("YEARLY");
        int from = 2000;
        int to = 2030;
        List<Integer> years = new ArrayList<>();
        while (from <= to) {
            years.add(from++);
        }
        yearPicker.getItems().addAll(years);
        handleTransactions();
    }

//    DISPLAY DATA IN TABLES METHODS
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
        buildTable(cashIncomeTable, createTableDataFromDate(TransactionType.CASH_INCOME));
    }

    public void showBankIncome() {
        bankIncome.setVisible(true);
        buildTable(bankIncomeTable, createTableDataFromDate(TransactionType.BANK_INCOME));
    }

    public void showCashOutcome() {
        cashOutcome.setVisible(true);
        buildTable(cashOutcomeTable, createTableDataFromDate(TransactionType.CASH_OUTCOME));
    }

    public void showBankOutcome() {
        bankOutcome.setVisible(true);
        buildTable(bankOutcomeTable, createTableDataFromDate(TransactionType.BANK_OUTCOME));
    }

    public void buildTable(TableView table, ObservableList data) {

        table.getColumns().clear();
        TableColumn date = new TableColumn("Date");
        TableColumn desc = new TableColumn("Description");
        TableColumn amnt = new TableColumn("Amount");
        TableColumn id = new TableColumn("ID");

        date.setCellValueFactory(new PropertyValueFactory<TransactionEntry, String>("date"));
        desc.setCellValueFactory(new PropertyValueFactory<TransactionEntry, String>("description"));
        amnt.setCellValueFactory(new PropertyValueFactory<TransactionEntry, String>("amount"));
        id.setCellValueFactory(new PropertyValueFactory<TransactionEntry, Long>("id"));

        id.setVisible(false);
        table.getColumns().addAll(id, date, desc, amnt);
        table.setItems(data);

// ↓↓↓ It does not resize the tables after first use of show(). Only when using second+ time. Dunno why. ↓↓↓
        table.setPrefWidth(0.45 * flowTables.getWidth());
        desc.setPrefWidth(0.65 * table.getWidth());
        date.setPrefWidth(0.2 * table.getWidth());
        amnt.setPrefWidth(0.1 * table.getWidth());

        date.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(date);
        table.sort();
    }

    public ObservableList<TransactionEntry> createTableDataFromDate(TransactionType type) {

        ObservableList<TransactionEntry> data = FXCollections.observableArrayList();

        switch (type) {
            case CASH_INCOME:
                createCashIncomeDataByDate(data);
                break;
            case BANK_INCOME:
                createBankIncomeDataByDate(data);
                break;
            case CASH_OUTCOME:
                createCashOutcomeDataByDate(data);
                break;
            case BANK_OUTCOME:
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
            fromRepo.forEach(e -> data.add(new CashIncomeEntry(e.getId(), e.getDate(), e.getDescription(), e.getAmount())));
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
            fromRepo.forEach(e -> data.add(new BankIncomeEntry(e.getId(), e.getDate(), e.getDescription(), e.getAmount())));
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
            fromRepo.forEach(e -> data.add(new CashOutcomeEntry(e.getId(), e.getDate(), e.getDescription(), e.getAmount())));
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
            fromRepo.forEach(e -> data.add(new BankOutcomeEntry(e.getId(), e.getDate(), e.getDescription(), e.getAmount())));
        }
        return data;
    }

//    ADD, DELETE, EDIT TRANSACTION METHODS
    public void handleTransactions() {
        addCashIncome.setOnAction(e -> addTransaction(TransactionType.CASH_INCOME));
        addBankIncome.setOnAction(e -> addTransaction(TransactionType.BANK_INCOME));
        addCashOutcome.setOnAction(e -> addTransaction(TransactionType.CASH_OUTCOME));
        addBankOutcome.setOnAction(e -> addTransaction(TransactionType.BANK_OUTCOME));

        deleteCashIncome.setOnAction(e -> deleteTransaction(TransactionType.CASH_INCOME));
        deleteBankIncome.setOnAction(e -> deleteTransaction(TransactionType.BANK_INCOME));
        deleteCashOutcome.setOnAction(e -> deleteTransaction(TransactionType.CASH_OUTCOME));
        deleteBankOutcome.setOnAction(e -> deleteTransaction(TransactionType.BANK_OUTCOME));

        editCashIncome.setOnAction(e -> editTransaction(TransactionType.CASH_INCOME));
        editBankIncome.setOnAction(e -> editTransaction(TransactionType.BANK_INCOME));
        editCashOutcome.setOnAction(e -> editTransaction(TransactionType.CASH_OUTCOME));
        editBankOutcome.setOnAction(e -> editTransaction(TransactionType.BANK_OUTCOME));
    }

    private void addTransaction(TransactionType type) {
        switch (type) {
            case CASH_INCOME:
                TransactionPopupBuilder.buildTransactionPopup();
                TransactionPopupBuilder.confirmButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty() && TransactionPopupBuilder.checkIfAmountIsNumber()) {
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        repo.createCashIncomeEntry(Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                TransactionPopupBuilder.cancelButton.setOnAction(e -> TransactionPopupBuilder.closeStage());
                break;

            case BANK_INCOME:
                TransactionPopupBuilder.buildTransactionPopup();
                TransactionPopupBuilder.okButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty()) {
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        repo.createBankIncomeEntry(Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                TransactionPopupBuilder.cancelButton.setOnAction(e -> TransactionPopupBuilder.closeStage());
                break;

            case CASH_OUTCOME:
                TransactionPopupBuilder.buildTransactionPopup();
                TransactionPopupBuilder.confirmButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty()) {
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        repo.createCashOutcomeEntry(Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                TransactionPopupBuilder.cancelButton.setOnAction(e -> TransactionPopupBuilder.closeStage());
                break;

            case BANK_OUTCOME:
                TransactionPopupBuilder.buildTransactionPopup();
                TransactionPopupBuilder.okButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty()) {
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        repo.createBankOutcomeEntry(Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                TransactionPopupBuilder.cancelButton.setOnAction(e -> TransactionPopupBuilder.closeStage());
        }
    }

    private void deleteTransaction(TransactionType type) {
        TransactionEntry selected;
        switch (type) {
            case CASH_INCOME:
                selected = (CashIncomeEntry) cashIncomeTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    TransactionEntryRepo repo = new TransactionEntryRepo();
                    CashIncomeEntry toDelete = repo.findCashIncomeById(selected.getId());
                    repo.deleteCashIncomeEntry(toDelete);
                    show();
                    break;
                }
            case BANK_INCOME:
                selected = (BankIncomeEntry) bankIncomeTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    TransactionEntryRepo repo = new TransactionEntryRepo();
                    BankIncomeEntry toDelete = repo.findBankIncomeById(selected.getId());
                    repo.deleteBankIncomeEntry(toDelete);
                    show();
                    break;
                }
            case CASH_OUTCOME:
                selected = (CashOutcomeEntry) cashOutcomeTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    TransactionEntryRepo repo = new TransactionEntryRepo();
                    CashOutcomeEntry toDelete = repo.findCashOutcomeById(selected.getId());
                    repo.deleteCashOutcomeEntry(toDelete);
                    show();
                    break;
                }
            case BANK_OUTCOME:
                selected = (BankOutcomeEntry) bankOutcomeTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    TransactionEntryRepo repo = new TransactionEntryRepo();
                    BankOutcomeEntry toDelete = repo.findBankOutcomeById(selected.getId());
                    repo.deleteBankOutcomeEntry(toDelete);
                    show();
                }
        }
    }

    private void editTransaction(TransactionType type) {
        TransactionEntry selected;
        String[] amountValues;

        switch (type) {
            case CASH_INCOME:
                selected = (CashIncomeEntry) cashIncomeTable.getSelectionModel().getSelectedItem();
                TransactionPopupBuilder.buildTransactionPopup(selected.getDate().toString(), selected.getDescription(), selected.getAmount().toString());
                TransactionPopupBuilder.confirmButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty() && TransactionPopupBuilder.checkIfAmountIsNumber()) {
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        CashIncomeEntry entryToUpdate = repo.findCashIncomeById(selected.getId());
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        repo.updateCashIncomeEntry(entryToUpdate, Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                break;

            case BANK_INCOME:
                selected = (BankIncomeEntry) bankIncomeTable.getSelectionModel().getSelectedItem();
                TransactionPopupBuilder.buildTransactionPopup(selected.getDate().toString(), selected.getDescription(), selected.getAmount().toString());
                TransactionPopupBuilder.confirmButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty() && TransactionPopupBuilder.checkIfAmountIsNumber()) {
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        BankIncomeEntry entryToUpdate = repo.findBankIncomeById(selected.getId());
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        repo.updateBankIncomeEntry(entryToUpdate, Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                break;

            case CASH_OUTCOME:
                selected = (CashOutcomeEntry) cashOutcomeTable.getSelectionModel().getSelectedItem();
                TransactionPopupBuilder.buildTransactionPopup(selected.getDate().toString(), selected.getDescription(), selected.getAmount().toString());
                TransactionPopupBuilder.confirmButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty() && TransactionPopupBuilder.checkIfAmountIsNumber()) {
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        CashOutcomeEntry entryToUpdate = repo.findCashOutcomeById(selected.getId());
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        repo.updateCashOutcomeEntry(entryToUpdate, Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                break;
            case BANK_OUTCOME:
                selected = (BankIncomeEntry) bankOutcomeTable.getSelectionModel().getSelectedItem();
                TransactionPopupBuilder.buildTransactionPopup(selected.getDate().toString(), selected.getDescription(), selected.getAmount().toString());
                TransactionPopupBuilder.confirmButton.setOnAction(e -> {
                    if (TransactionPopupBuilder.checkIfFieldsNotEmpty() && TransactionPopupBuilder.checkIfAmountIsNumber()) {
                        TransactionEntryRepo repo = new TransactionEntryRepo();
                        BankOutcomeEntry entryToUpdate = repo.findBankOutcomeById(selected.getId());
                        String[] newTrans = TransactionPopupBuilder.getNewTransData();
                        repo.updateBankOutcomeEntry(entryToUpdate, Date.valueOf(newTrans[0]), newTrans[1], Double.valueOf(newTrans[2]));
                        show();
                    }
                });
                break;
        }
    }
}
