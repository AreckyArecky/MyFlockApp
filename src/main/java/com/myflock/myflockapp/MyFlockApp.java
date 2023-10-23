package com.myflock.myflockapp;

import com.myflock.myflockapp.auth.UserRepo;
import com.myflock.myflockapp.view.loginview.LoginViewController;
import com.myflock.myflockapp.repo.ChurchMemberRepo;
import com.myflock.myflockapp.entity.ChurchMember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.mychurch.mychurchapp.view.LoginView;
//import com.mychurch.mychurchapp.view.MainView;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

/**
 *
 * @author Areckyy_
 */
public class MyFlockApp extends Application {

    @Override
    public void start(Stage stage) {

        try {
            URL fxmlResource = LoginViewController.class.getResource("fxml/LoginView.fxml");

            Parent parent = FXMLLoader.load(fxmlResource);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    public static void main(String[] args) {
        ChurchMemberRepo memberRepo = new ChurchMemberRepo();
        memberRepo.createMember("Arkadiusz", "Kowalski", 20);

//        Scanner scanner = new Scanner(System.in);
//
//        Scanner scanner = new Scanner(System.in);
        launch(args);
    }

}
