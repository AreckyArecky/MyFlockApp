/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.repo;

import com.myflock.myflockapp.entity.finances.BankIncomeEntry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.myflock.myflockapp.entity.finances.TransactionEntry;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class TransactionEntryRepo {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em = emf.createEntityManager();

//   BANK INCOME ENTRY CREATE - EDIT - DELETE - FIND_BY_DATE
    public void createBankIncomeEntry(Date date, String desc, Double amount) {
        BankIncomeEntry entry = new BankIncomeEntry(date, desc, amount);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(entry);
        trans.commit();
    }

    public void updateBankIncomeEntry(BankIncomeEntry entry, Date date, String desc, Double amount) {
        EntityTransaction trans = em.getTransaction();
        entry.setDate(date);
        entry.setDescription(desc);
        entry.setAmount(amount);
        em.persist(entry);
        trans.commit();
    }

    public void deleteBankIncomeEntry(BankIncomeEntry entry) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.remove(entry);
        trans.commit();
    }

    public Collection<BankIncomeEntry> findBankIncomeEntryByYear(int year) {

        Query q = em.createNativeQuery("SELECT * FROM BankIncomeEntry WHERE YEAR(date)= :date", BankIncomeEntry.class);
        q.setParameter("date", year);
        Collection<BankIncomeEntry> result = (List<BankIncomeEntry>) q.getResultList();
        result.forEach(r -> System.out.println(r.toString()));
        return result;
    }

//    BANK OUTCOME ENTRY CREATE - EDIT - DELETE - FIND_BY-DATE
//    CASH INCOME ENTRY CREATE - EDIT - DELETE - FIND_BY_DATE
//    CASH OUTCOME ENTRY CREATE - EDIT - DELETE - FIND_BY_DATE
}
