/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.view.mainview;

import com.myflock.myflockapp.entity.ChurchMember;
import com.myflock.myflockapp.repo.ChurchMemberRepo;
import java.util.Collection;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author DevelopmentMPOS
 */
public class MediaController {

    private Button add;
    private Button delete;
    private Button edit;
    private TableView memList;
    private Tab memTab;
    private Boolean isActive;

    public MediaController() {

        add = new Button("ADD");
        delete = new Button("DELETE");
        edit = new Button("EDIT");
        memList = new TableView();
        memTab = new Tab("MEMBERS");
        isActive = false;
    }

    public void init(VBox sidePanel, TabPane mainTab) {

        buildLayout();
        sidePanel.getChildren().addAll(add, delete, edit);

        mainTab.getTabs().add(memTab);

        isActive = true;

    }

    public boolean isActive() {
        return isActive;
    }

    public void buildLayout() {

        buildTable();
        memTab.setContent(memList);
        memTab.setClosable(true);

    }

    public void buildTable() {
        TableColumn<ChurchMember, String> firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<ChurchMember, String> lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        TableColumn<ChurchMember, String> ageCol = new TableColumn("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<ChurchMember, String> isMemberCol = new TableColumn("Membership");
        isMemberCol.setCellValueFactory(new PropertyValueFactory<>("isMember"));
        TableColumn<ChurchMember, String> serviceCol = new TableColumn("Service");
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("service"));
        TableColumn<ChurchMember, String> phoneCol = new TableColumn("Phone number");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        memList.getColumns().addAll(firstNameCol, lastNameCol, ageCol, isMemberCol, serviceCol, phoneCol);
        
        memList.setFixedCellSize(30);
        ChurchMemberRepo memRepo = new ChurchMemberRepo();
        Collection memListData = memRepo.getAllMembers();

        memListData.forEach(a -> memList.getItems().add(a));

    }

}
