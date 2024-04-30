/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.entity.finances;

import jakarta.persistence.Entity;
import java.sql.Date;

/**
 *
 * @author DevelopmentMPOS
 */
@Entity
public class BankOutcomeEntry extends TransactionEntry{
    public BankOutcomeEntry() {
        super();
    }
    public BankOutcomeEntry(Date date, String description, Double amount) {
        super(date, description, amount);
    }
}
