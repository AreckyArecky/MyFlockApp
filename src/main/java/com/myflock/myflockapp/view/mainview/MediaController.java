/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.view.mainview;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author DevelopmentMPOS
 */
public class MediaController {

    private VBox menu;
    private Button add;
    private Button delete;
    private Button edit;
    private TableView memList;
    private Tab memTab;
    private Boolean isActive;

    public MediaController() {
        menu = new VBox();
        add = new Button("ADD");
        delete = new Button("DELETE");
        edit = new Button("EDIT");
        memList = new TableView();
        memTab = new Tab("MEMBERS");
        isActive = false;
    }

    public void init(Pane sidePanel, TabPane mainTab) {
        
        buildLayout();
        sidePanel.getChildren().add(menu);

        mainTab.getTabs().add(memTab);
        
        isActive = true;

    }
    
    public boolean isActive(){
        return isActive;
    }
    
    public void buildLayout(){
        menu.getChildren().addAll(add, delete, edit);
        menu.setId("sideMenu");
        
        
        buildTable();
        memTab.setContent(memList);
        memTab.setClosable(true);
        
    }
    public void buildTable(){
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn ageCol = new TableColumn("Age");
        TableColumn isMemberCol = new TableColumn("Membership");
        TableColumn serviceCol = new TableColumn("Service");
        TableColumn phoneCol = new TableColumn("Phone number");
        memList.getColumns().addAll(firstNameCol, lastNameCol, ageCol, isMemberCol, serviceCol, phoneCol);
    }
    public void getData(){
//        memList.get
    }

}
