package com.myflock.myflockapp;

import com.myflock.myflockapp.view.loginview.LoginViewController;
import com.myflock.myflockapp.repo.ChurchMemberRepo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
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
       
        
        launch(args);
    }

}
