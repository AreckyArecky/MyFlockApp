/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myflock.myflockapp.view.mainview;

import com.myflock.myflockapp.auth.UserLogged;
import com.myflock.myflockapp.entity.ChurchMember;
import com.myflock.myflockapp.repo.ChurchMemberRepo;
import com.myflock.myflockapp.view.financesview.FinancesViewController;
import com.myflock.myflockapp.view.loginview.LoginViewController;
import com.myflock.myflockapp.view.userpanelview.UserPanelViewController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Areckyy_
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
    private Label memMsg;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // DISPLAY LOGGED USER
        userLabel.setText(UserLogged.user.getUsername());

        // ADD LOCAL TIME VIEWER
        addLocalTime();

        // SHOW ONLY 'WELCOME' TAB
        clearTabs();

        buildTable();

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
            UserLogged.logout();

        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    @FXML
    public void initMembers() {
        clearTabs();
        mainTab.getTabs().add(memTab);
        mainTab.getSelectionModel().select(memTab);

    }

    @FXML
    public void initFinances() {
        try {
            clearTabs();

            FXMLLoader finLoader = new FXMLLoader(getClass().getResource("/com/myflock/myflockapp/view/finances/fxml/FinancesView.fxml"));
            VBox finContent = finLoader.load();
            FinancesViewController financesController = finLoader.getController();

            // Do something with the child node and controller
            finTab.setContent(finContent);
            mainTab.getTabs().add(finTab);
            mainTab.getSelectionModel().select(finTab);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
//        ageCol.setCellFactory(TextFieldTableCell.<ChurchMember>forTableColumn());
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
        Border border = new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        StringBuilder memMsgTxt = new StringBuilder();
        memMsgTxt.append("You must enter");
        if (firstName.getText().isEmpty()) {
            clearBorders();
            firstName.setBorder(border);
            memMsgTxt.append("first name.");
            memMsg.setText(memMsgTxt.toString());

        } else if (lastName.getText().isEmpty()) {
            clearBorders();
            memMsgTxt.append(" last name.");
            lastName.setBorder(border);
            memMsg.setText(memMsgTxt.toString());
        } else if (age.getText().isEmpty()) {
            clearBorders();
            memMsgTxt.append(" age.");
            age.setBorder(border);
            memMsg.setText(memMsgTxt.toString());
        } else {
            ChurchMemberRepo memRepo = new ChurchMemberRepo();
            if (isMemberResult() == true && service.getText().isEmpty() && phoneNum.getText().isEmpty()) {
                String firstName = this.firstName.getText();
                String lastName = this.lastName.getText();
                int passAge = Integer.parseInt(this.age.getText());
                System.out.println("Nic nie zaznaczone?" + isMemberResult());

                memRepo.createMember(firstName, lastName, passAge);
            } else if (service.getText().isEmpty() && phoneNum.getText().isEmpty()) {
                String firstName = this.firstName.getText();
                String lastName = this.lastName.getText();
                int passAge = Integer.parseInt(this.age.getText());
                boolean isMember = isMemberResult();

                memRepo.createMember(firstName, lastName, passAge, isMember);
                System.out.println("Zaznaczone false." + isMember);
                memRepo.getAllMembers().toString();
            } else if (phoneNum.getText().isEmpty()) {
                String firstName = this.firstName.getText();
                String lastName = this.lastName.getText();
                int passAge = Integer.parseInt(this.age.getText());
                boolean isMember = isMemberResult();
                String service = this.service.getText();
                memRepo.createMember(firstName, lastName, passAge, isMember, service);
            } else if (service.getText().isEmpty()) {
                String firstName = this.firstName.getText();
                String lastName = this.lastName.getText();
                int passAge = Integer.parseInt(this.age.getText());
                boolean isMember = isMemberResult();
                int phoneNum = Integer.parseInt(this.phoneNum.getText());
                memRepo.createMember(firstName, lastName, passAge, isMember, phoneNum);
            } else {
                String firstName = this.firstName.getText();
                String lastName = this.lastName.getText();
                int passAge = Integer.parseInt(this.age.getText());
                boolean isMember = isMemberResult();
                String service = this.service.getText();
                int phoneNum = Integer.parseInt(this.phoneNum.getText());
                memRepo.createMember(firstName, lastName, passAge, isMember, service, phoneNum);
            }

            memTable.scrollTo(memTable.getItems().size());
            memTable.getSelectionModel().select(memTable.getItems().size());

            populateTable();
            memMsg.setText("* Fields are required.");
            clearLabels();
        }
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

    public void buildEditPopup() {
        ChurchMember edited = getMemberToEdit();

        VBox editPopup = new VBox();
        HBox editData = new HBox();
        HBox buttons = new HBox();
        Label editTxt = new Label("EDIT USER:");
        TextField firstName = new TextField(edited.getFirstName());
        firstName.setPromptText("First name");
        TextField lastName = new TextField(edited.getSecondName());
        lastName.setPromptText("Last name");
        TextField age = new TextField(String.valueOf(edited.getAge()));
        age.setPromptText("Age");
        Label isMemberTxt = new Label("Member?");
        RadioButton yesMem = new RadioButton("Yes");
        RadioButton noMem = new RadioButton("No");
        TextField service = new TextField(edited.getService());
        TextField phoneNum = new TextField(String.valueOf(edited.getPhoneNumber()));
        ToggleGroup isMemberGroup = new ToggleGroup();
        isMemberGroup.getToggles().addAll(yesMem, noMem);
        Button save = new Button("SAVE");
        save.setPrefWidth(80);
        Button cancel = new Button("CANCEL");
        cancel.setPrefWidth(80);

        editData.getChildren().addAll(editTxt, firstName, lastName, age, isMemberTxt, yesMem, noMem, service, phoneNum);
        buttons.getChildren().addAll(save, cancel);
        editPopup.getChildren().addAll(editData, buttons);

        editPopup.setSpacing(20);
        editPopup.setPadding(new Insets(30));
        editData.setSpacing(20);
        editData.setAlignment(Pos.CENTER);

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(30);
        if (edited.getIsMember()) {
            yesMem.setSelected(true);
        } else {
            noMem.setSelected(true);
        }
        Scene popup = new Scene(editPopup);
        popup.getStylesheets().add("/styles/editpopup.css");
        Stage popupWindow = new Stage();
        popupWindow.setScene(popup);
        popupWindow.setAlwaysOnTop(true);
        popupWindow.show();
        final ChurchMember toEdit = edited;

        save.setOnAction(s -> {
            ChurchMemberRepo memRepo = new ChurchMemberRepo();
            boolean isMember = yesMem.isSelected() ? true : false;
            memRepo.updateMember(toEdit, firstName.getText(), lastName.getText(), Integer.parseInt(age.getText()), isMember, service.getText(), Integer.parseInt(age.getText()));
            populateTable();
            popupWindow.close();
        });

        cancel.setOnAction(c -> popupWindow.close());
    }

    public ChurchMember getMemberToEdit() {
        ChurchMemberRepo memRepo = new ChurchMemberRepo();
        return (ChurchMember) memTable.getSelectionModel().getSelectedItem();

    }

    @FXML
    public void clearLabels() {
        firstName.clear();
        lastName.clear();
        age.clear();
        yesMem.setSelected(false);
        noMem.setSelected(false);
        phoneNum.clear();
        service.clear();
        clearBorders();
    }

    public void clearBorders() {
        firstName.setBorder(Border.EMPTY);
        lastName.setBorder(Border.EMPTY);
        age.setBorder(Border.EMPTY);
    }

    public boolean isMemberResult() {
        boolean isMember = true;
        if (noMem.isSelected()) {
            isMember = false;
        }
        return isMember;
    }

    public void openPnl() {
        try {

            if (UserLogged.user.getIsAdmin()) {
                URL fxmlResource = UserPanelViewController.class
                        .getResource("fxml/UserPanelView.fxml");
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(fxmlResource)));
                stage.setAlwaysOnTop(true);
                stage.show();
            } else {
                notAnAdmin();
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void notAnAdmin() {

        VBox warningBox = new VBox();
        Label notAnAdmin = new Label("User not allowed to use this function!");
        Button ok = new Button("OK");
        warningBox.getChildren().addAll(notAnAdmin, ok);
        Scene scene = new Scene(warningBox);
        Stage warningPopup = new Stage();
        warningPopup.setScene(scene);
        warningPopup.show();
        ok.setOnAction(e -> {
            warningPopup.close();
        });
    }
}
