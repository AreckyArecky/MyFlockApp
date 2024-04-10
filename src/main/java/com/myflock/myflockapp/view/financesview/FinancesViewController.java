/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myflock.myflockapp.view.financesview;

import com.myflock.myflockapp.repo.TransactionEntryRepo;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author DevelopmentMPOS
 */
public class FinancesViewController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void show() {
        TransactionEntryRepo transEntryRepo = new TransactionEntryRepo();
        transEntryRepo.findBankIncomeEntryByYear(2024);
      

    }

}
