/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Screen;

/**
 *
 * @author DevelopmentMPOS
 */
public class MainView extends Application {

    public void start(Stage window) {

        Label welcome = new Label("WELCOME!");
        welcome.setAlignment(Pos.CENTER);
        welcome.setFont(new Font("Calibri", 50));

        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();

        Scene main = new Scene(welcome, width, height);

        window.setScene(main);
        window.show();
        window.setX(0);
        window.setY(0);
//        window.setFullScreen(true);

    }
}
