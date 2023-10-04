/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myflock.myflockapp.view.loginview;

import com.myflock.myflockapp.MyFlockApp;
import com.myflock.myflockapp.auth.UserRepo;
import com.myflock.myflockapp.view.LoginViewer;
import com.myflock.myflockapp.view.MainView;
import com.myflock.myflockapp.view.mainview.MainViewController;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author DevelopmentMPOS
 */
public class LoginViewController implements Initializable {

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField authUsr;

    @FXML
    private PasswordField authPass;

    @FXML
    private Label authLabel;

    @FXML
    private Button authBtnLogin;

    @FXML
    private Button authBtnExit;

    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Initializes the controller class.
     *
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Auto-generated method stub
        loginAnchorPane.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        loginAnchorPane.setOnMouseDragged((MouseEvent event) -> {
            Stage stage = (Stage) loginAnchorPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    public void handleExit() {

        Stage stage = (Stage) authBtnExit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void enterPressed(KeyEvent event) throws IOException {
        KeyCode kc = event.getCode();
        if (kc == KeyCode.ENTER) {
            handleLogin();
        }
    }

    @FXML
    private void handleLogin() {
        boolean logged = false;
        try {

            UserRepo repo = new UserRepo();
            if (fieldsEmpty()) {
                fieldsEmpty();
                logged = false;
            } else if (fieldsEmpty() == false && repo.auth(authUsr.getText(), authPass.getText())) {
                authLabel.setText("Login successful!");
                logged = true;

//                MainView main = new MainView();
//                main.start(window); 
            } else {
                authLabel.setText("Incorrect username or password!");
                logged = false;

            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(LoginViewer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        if (logged == true) {
            switchSceneToMain();
        }
    }

    @FXML
    private boolean fieldsEmpty() {
        if (authUsr.getText().isEmpty()) {
            authLabel.setText("You must enter username!");
            return true;

        } else if (authPass.getText().isEmpty()) {
            authLabel.setText("You must enter password!");
            return true;
        } else {

            return false;
        }
    }

//    SWITCH TO MAIN SCENE
    @FXML
    public void switchSceneToMain() {

        try {
            URL fxmlResource = MainViewController.class
                    .getResource("fxml/MainView.fxml");
            Parent parent = FXMLLoader.load(fxmlResource);
            Stage stage = (Stage) loginAnchorPane.getScene().getWindow();
            stage.setScene(new Scene(parent));
        } catch (IOException ex) {
            ex.getMessage();
        }

    }
}
