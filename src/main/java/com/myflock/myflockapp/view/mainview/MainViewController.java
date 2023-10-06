/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myflock.myflockapp.view.mainview;

import com.myflock.myflockapp.auth.UserLogged;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevelopmentMPOS
 */
public class MainViewController implements Initializable {

    @FXML
    private Label time;

    @FXML
    private Label userLabel;

    @FXML
    private AnchorPane mainViewAnchorPane;

    /**
     * Initializes the controller class.
     */
    @FXML
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        userLabel.setText(UserLogged.user.getUsername());

        AnimationTimer timer = new AnimationTimer() {
            @Override

            public void handle(long now) {
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();
    }

}
