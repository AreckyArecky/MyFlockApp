/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myflock.myflockapp.view.mainview;

import com.myflock.myflockapp.auth.UserLogged;
import com.myflock.myflockapp.entity.ChurchMember;
import com.myflock.myflockapp.repo.ChurchMemberRepo;
import com.myflock.myflockapp.view.loginview.LoginViewController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DevelopmentMPOS
 */
public class MainViewController implements Initializable {

    @FXML
    private TabPane mainTab;

    @FXML
    private Tab welcomeTab;

    @FXML
    private Tab memTab;

    @FXML
    private Tab finTab;

    @FXML
    private Tab medTab;

    @FXML
    private VBox sidePanel;

    @FXML
    private Label time;

    @FXML
    private Label userLabel;

    @FXML
    private AnchorPane mainViewAnchorPane;

    @FXML
    private Button logBtn;

    @FXML
    private Button members;

    @FXML
    private VBox memPanel;

    @FXML
    private VBox finPanel;

    @FXML
    private TableView memTable;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField age;

    @FXML
    private TextField service;

    @FXML
    private RadioButton yesMem;

    @FXML
    private RadioButton noMem;

    @FXML
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        // DISPLAY LOGGED USER
        userLabel.setText(UserLogged.user.getUsername());

        // ADD LOCAL TIME VIEWER
        addLocalTime();

        // SHOW ONLY 'WELCOME' TAB
        clearTabs();
    }

    @FXML
    public void logout() {

        try {
            URL fxmlResource = LoginViewController.class
                    .getResource("fxml/LoginView.fxml");
            Parent parent = FXMLLoader.load(fxmlResource);
            Stage currentStage = (Stage) mainViewAnchorPane.getScene().getWindow();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.show();
            currentStage.close();

        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    @FXML
    public void initMembers() {
        clearTabs();
        mainTab.getTabs().add(memTab);
        mainTab.getSelectionModel().select(memTab);
        buildTable();
    }
//
//    @FXML
//    public void updateMembers() {
//        
//        mediaController.updateTable(mainTab);
//        mainTab.getSelectionModel().select(1);
//
//    }
//        mainViewAnchorPane.getScene().getStylesheets().add(css);

    @FXML
    public void initFinances() {
        clearTabs();
        mainTab.getTabs().add(finTab);
        mainTab.getSelectionModel().select(finTab);

    }

    @FXML
    public void initMedia() {
        clearTabs();
        mainTab.getTabs().add(medTab);
        mainTab.getSelectionModel().select(medTab);
    }

    @FXML
    public void clearTabs() {

        mainTab.getTabs().clear();
        mainTab.getTabs().add(welcomeTab);

    }

    @FXML
    public void addLocalTime() {
        AnimationTimer timer = new AnimationTimer() {
            @Override

            public void handle(long now) {
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();
    }

    public void buildTable() {
        TableColumn<ChurchMember, String> idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<ChurchMember, String> firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setMinWidth(120);
        TableColumn<ChurchMember, String> lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        lastNameCol.setMinWidth(120);
        TableColumn<ChurchMember, String> ageCol = new TableColumn("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<ChurchMember, String> isMemberCol = new TableColumn("Membership");
        isMemberCol.setCellValueFactory(new PropertyValueFactory<>("isMember"));
        TableColumn<ChurchMember, String> serviceCol = new TableColumn("Service");
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("service"));
        TableColumn<ChurchMember, String> phoneCol = new TableColumn("Phone number");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneCol.setMinWidth(120);

        memTable.getColumns().addAll(idCol, firstNameCol, lastNameCol, ageCol, isMemberCol, serviceCol, phoneCol);

        memTable.setFixedCellSize(30);

        populateTable();
    }

    public void populateTable() {
        memTable.getItems().clear();
        ChurchMemberRepo memRepo = new ChurchMemberRepo();
        Collection memListData = memRepo.getAllMembers();

        memListData.forEach(a -> memTable.getItems().add(a));

    }

    public void addMemberAction() {
        ChurchMemberRepo memRepo = new ChurchMemberRepo();
        memRepo.createMember(firstName.getText(), lastName.getText(), Integer.valueOf(age.getText()));
        populateTable();

        memTable.scrollTo(memTable.getItems().size());
        memTable.getSelectionModel().select(memTable.getItems().size());

    }

    public void deleteMemberAction() {
        ChurchMemberRepo memRepo = new ChurchMemberRepo();
        ChurchMember selected = (ChurchMember) memTable.getSelectionModel().getSelectedItem();
        int selectedID = selected.getId();

        if (selected != null) {
            ChurchMember memToDelete = memRepo.findById(selectedID);
            memRepo.deleteMember(memToDelete);
            populateTable();
        }

    }

}
