/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author DevelopmentMPOS
 */
import com.mychurch.mychurchapp.auth.repo.UserRepo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class LoginView extends Application {

    public void start(Stage window) {

        VBox loginContent = new VBox();
        loginContent.setAlignment(Pos.CENTER);
        loginContent.setPadding(new Insets(20));

        Label welcome = new Label("Welcome!");
        welcome.setFont(new Font("Calibri", 30));
        welcome.setPadding(new Insets(0, 0, 20, 0));

        Label loginInfo = new Label("Please enter your username and password to login.");
        loginInfo.setFont(new Font("Calibri", 20));
        loginInfo.setPadding(new Insets(0, 0, 40, 0));

        Label usernameLabel = new Label("Username: ");
        usernameLabel.setPadding(new Insets(0, 20, 0, 0));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

//        HBox username = new HBox(usernameLabel, usernameField);
//        username.setAlignment(Pos.CENTER);
//        username.setPadding(new Insets(0, 0, 20, 0));
//        username.setMaxWidth(300);
        Label pwdLabel = new Label("Password: ");
        pwdLabel.setPadding(new Insets(0, 20, 0, 0));

        PasswordField pwdField = new PasswordField();
        pwdField.setPromptText("Enter password");

//        HBox pwd = new HBox(pwdLabel, pwdField);
//        pwd.setAlignment(Pos.CENTER);
//        pwd.setPadding(new Insets(0, 0, 40, 0));
//        pwd.setMaxWidth(300);
        GridPane crdBox = new GridPane();
        crdBox.add(usernameLabel, 0, 0);
        crdBox.add(usernameField, 1, 0);
        crdBox.add(pwdLabel, 0, 1);
        crdBox.add(pwdField, 1, 1);
        crdBox.setAlignment(Pos.CENTER);
        crdBox.maxWidth(300);
        crdBox.setPadding(new Insets(0, 0, 40, 0));
        crdBox.setVgap(20);

        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(60);
        Button exitBtn = new Button("Exit");
        exitBtn.setMinWidth(60);
        HBox btns = new HBox(loginBtn, exitBtn);
        btns.setAlignment(Pos.CENTER);
        btns.setSpacing(30);
        btns.setPadding(new Insets(0, 20, 30, 0));

        Label loginMsg = new Label("");

        loginContent.getChildren().addAll(welcome, loginInfo, crdBox, btns, loginMsg);

        Scene login = new Scene(loginContent, 600, 400);
        window.setScene(login);
        window.show();

        exitBtn.setOnAction((event) -> {
            window.close();
        });

        loginBtn.setOnAction((event) -> {
            UserRepo repo = new UserRepo();
            if (usernameField.getText().isEmpty()) {
                loginMsg.setText("You must enter username!");
            } else if (pwdField.getText().isEmpty()) {
                loginMsg.setText("You must enter password!");
            }
            if (repo.auth(usernameField.getText(), pwdField.getText())) {
                loginMsg.setText("Login successful!");

                MainView main = new MainView();
                main.start(window);

            } else {
                loginMsg.setText("Incorrect username or password!");

            }

        }
        );

    }
}
