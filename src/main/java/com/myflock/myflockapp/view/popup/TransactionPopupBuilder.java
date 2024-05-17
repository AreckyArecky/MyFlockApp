/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.view.popup;

import static com.myflock.myflockapp.view.popup.PopupBuilder.cancelButton;
import static com.myflock.myflockapp.view.popup.PopupBuilder.okButton;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TransactionPopupBuilder extends PopupBuilder {

    static String date;
    static String desc;
    static String amnt;
    static DatePicker datePicker;
    static TextField descField;
    static TextField amntField;

    public static Button confirmButton = new Button("Confirm");
    static Stage newStage = new Stage();

    public TransactionPopupBuilder() {

    }

    public static void buildTransactionPopup() {
        HBox popup = new HBox();
        Label dateLabel = new Label("Date:");
        Label descLabel = new Label("Description:");
        Label amntLabel = new Label("Amount:");
        datePicker = new DatePicker();
        descField = new TextField();
        descField.promptTextProperty().set("Enter description...");
        amntField = new TextField();

        amntField.setPrefWidth(50);
        popup.getChildren().addAll(dateLabel, datePicker, descLabel, descField, amntLabel, amntField, cancelButton, confirmButton);

        popup.setSpacing(10);
        popup.setPadding(new Insets(20));
        popup.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(popup);

        newStage.setScene(popupScene);
        newStage.show();

    }

    public static void buildTransactionPopup(String editedDate, String editedDesc, String editedAmnt) {
        HBox popup = new HBox();
        Label dateLabel = new Label("Date:");
        Label descLabel = new Label("Description:");
        Label amntLabel = new Label("Amount:");
        datePicker = new DatePicker();
        descField = new TextField();
        descField.promptTextProperty().set("Enter description...");
        amntField = new TextField();

        datePicker.setValue(LocalDate.parse(editedDate));
        descField.setText(editedDesc);
        amntField.setText(editedAmnt);

        amntField.setPrefWidth(50);
        popup.getChildren().addAll(dateLabel, datePicker, descLabel, descField, amntLabel, amntField, cancelButton, confirmButton);

        popup.setSpacing(20);
        popup.setPadding(new Insets(20));
        popup.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(popup);

        newStage.setScene(popupScene);
        newStage.show();

    }

    public static String[] getNewTransData() {
        TransactionPopupBuilder.date = datePicker.getValue().toString();
        TransactionPopupBuilder.desc = descField.getText();
        if (amntField.getText().contains(",")) {
            String[] amntParts = amntField.getText().split(",");
            TransactionPopupBuilder.amnt = amntParts[0] + "." + amntParts[1];
        } else {
            TransactionPopupBuilder.amnt = amntField.getText();
        }
//        if (amntSecond != null && !amntSecond.getText().isEmpty()) {
//            TransactionPopupBuilder.amnt = amntFirst.getText() + "." + amntSecond.getText();
//        } else {
//            TransactionPopupBuilder.amnt = amntFirst.getText() + "." + 00;
//        }

        closeStage();
        return new String[]{date, desc, amnt};
    }

    public static void closeStage() {
        newStage.close();
    }

    public static boolean checkIfFieldsNotEmpty() {
        if (datePicker.getValue() == null || descField.getText().isEmpty() || amntField.getText().isEmpty()) {
            buildInfoPopup("Some fields are empty!");
            return false;
        }
        return true;
    }

    public static boolean checkIfAmountIsNumber() {
        if (amntField.getText().matches("[0-9]+" + "[,.]?" + "[0-9]+")) {
            return true;
        } else {
            buildInfoPopup("Amount must be a number!");
            return false;
        }
    }

}
