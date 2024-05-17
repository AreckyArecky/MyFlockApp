/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.view.popup;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupBuilder {

    private String message;
    public static Button okButton = new Button("OK");
    public static Button cancelButton = new Button("CANCEL");
    public static String[] newTrans = new String[3];

    public PopupBuilder() {
        this.message = "Default message.";
    }

    public static void buildInfoPopup(String message) {
        Label messageLabel = new Label(message);
        VBox popup = new VBox();
        popup.getChildren().addAll(messageLabel, okButton);

        popup.setSpacing(20);
        popup.setPadding(new Insets(20));
        popup.setAlignment(Pos.CENTER);

        Scene popupScene = new Scene(popup);
        Stage newStage = new Stage();
        newStage.setScene(popupScene);
        newStage.show();
        okButton.setOnAction(e -> {
            newStage.close();
        });
    }

}
