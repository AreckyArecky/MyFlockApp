package com.myflock.myflockapp.view.userpanelview;

import com.myflock.myflockapp.auth.User;
import com.myflock.myflockapp.auth.UserRepo;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Areckyy
 */
public class UserPanelViewController implements Initializable {

    @FXML
    private TableView usrTable;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button addBtn;

    @FXML
    private RadioButton yesAd;

    @FXML
    private RadioButton noAd;

    @FXML
    private HBox editPopup;

    private User selected = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        buildUserTable();
    }

    void buildUserTable() {
        TableColumn<User, String> idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<User, String> usernameCol = new TableColumn("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setMinWidth(120);
        TableColumn<User, String> isAdminCol = new TableColumn("Admin");
        isAdminCol.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        usrTable.getColumns().addAll(idCol, usernameCol, isAdminCol);

        usrTable.setFixedCellSize(30);

        populateTable();
    }

    void populateTable() {
        usrTable.getItems().clear();
        UserRepo usrRepo = new UserRepo();
        Collection memListData = usrRepo.getAllUsers();

        memListData.forEach(a -> usrTable.getItems().add(a));
    }

    public void addUser() {
        UserRepo usrRepo = new UserRepo();
        try {
            usrRepo.createUser(usernameField.getText(), passwordField.getText());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UserPanelViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateTable();
    }

    public void deleteUser() {
        UserRepo usrRepo = new UserRepo();
        selected = (User) usrTable.getSelectionModel().getSelectedItem();
        int selectedID = selected.getId();

        if (selected != null) {
            User usrToDelete = usrRepo.findById(selectedID);
            usrRepo.deleteUser(usrToDelete);
            populateTable();
        }
    }

    public void editUser() {

        selected = (User) usrTable.getSelectionModel().getSelectedItem();

        usernameField.setText(selected.getUsername());
        if (selected.getIsAdmin()) {
            yesAd.setSelected(true);
        } else {
            noAd.setSelected(true);
        }
        editPopup.setVisible(true);

    }

    public void editSave() {
        UserRepo usrRepo = new UserRepo();
        int selectedID = selected.getId();
        boolean isAdmin = yesAd.isSelected() ? true : false;

        if (selected != null) {

            User userToUpdate = usrRepo.findById(selectedID);
            if (passwordField.getText().isEmpty()) {
                usrRepo.updateUserNoPass(userToUpdate, usernameField.getText(), isAdmin);
            } else {
                usrRepo.updateUser(userToUpdate, usernameField.getText(), passwordField.getText(), isAdmin);
            }
        }
        clearFields();
        editPopup.setVisible(false);
        populateTable();

    }

    public void editCancel() {
        clearFields();
        editPopup.setVisible(false);
        populateTable();
    }

    public void clearFields() {
        usernameField.clear();
        passwordField.clear();
        yesAd.setSelected(false);
        noAd.setSelected(false);
    }

}
