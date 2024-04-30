/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.repo;

import com.myflock.myflockapp.entity.finances.BankIncomeEntry;
import com.myflock.myflockapp.entity.finances.BankOutcomeEntry;
import com.myflock.myflockapp.entity.finances.CashIncomeEntry;
import com.myflock.myflockapp.entity.finances.CashOutcomeEntry;
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

    public Collection<BankIncomeEntry> findBankIncomeEntryByYearAndMonth(int year, int month) {

        Query q = em.createNativeQuery("SELECT * FROM BankIncomeEntry WHERE YEAR(date)= :year AND MONTH(date)= :month", BankIncomeEntry.class);
        q.setParameter("year", year);
        q.setParameter("month", month);
        Collection<BankIncomeEntry> result = (List<BankIncomeEntry>) q.getResultList();
        return result;
    }

//    BANK OUTCOME ENTRY CREATE - EDIT - DELETE - FIND_BY-DATE
    public void createBankOutcomeEntry(Date date, String desc, Double amount) {
        BankOutcomeEntry entry = new BankOutcomeEntry(date, desc, amount);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(entry);
        trans.commit();
    }

    public void updateBankOutcomeEntry(BankOutcomeEntry entry, Date date, String desc, Double amount) {
        EntityTransaction trans = em.getTransaction();
        entry.setDate(date);
        entry.setDescription(desc);
        entry.setAmount(amount);
        em.persist(entry);
        trans.commit();
    }

    public void deleteBankOutcomeEntry(BankOutcomeEntry entry) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.remove(entry);
        trans.commit();
    }

    public Collection<BankOutcomeEntry> findBankOutcomeEntryByYear(int year) {

        Query q = em.createNativeQuery("SELECT * FROM BankOutcomeEntry WHERE YEAR(date)= :date", BankOutcomeEntry.class);
        q.setParameter("date", year);
        Collection<BankOutcomeEntry> result = (List<BankOutcomeEntry>) q.getResultList();
        result.forEach(r -> System.out.println(r.toString()));
        return result;
    }

    public Collection<BankOutcomeEntry> findBankOutcomeEntryByYearAndMonth(int year, int month) {

        Query q = em.createNativeQuery("SELECT * FROM BankOutcomeEntry WHERE YEAR(date)= :year AND MONTH(date)= :month", BankIncomeEntry.class);
        q.setParameter("year", year);
        q.setParameter("month", month);
        Collection<BankOutcomeEntry> result = (List<BankOutcomeEntry>) q.getResultList();
        result.forEach(r -> System.out.println(r.toString()));
        return result;
    }
//    CASH INCOME ENTRY CREATE - EDIT - DELETE - FIND_BY_DATE

    public void createCashIncomeEntry(Date date, String desc, Double amount) {
        CashIncomeEntry entry = new CashIncomeEntry(date, desc, amount);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(entry);
        trans.commit();
    }

    public void updateCashIncomeEntry(CashIncomeEntry entry, Date date, String desc, Double amount) {
        EntityTransaction trans = em.getTransaction();
        entry.setDate(date);
        entry.setDescription(desc);
        entry.setAmount(amount);
        em.persist(entry);
        trans.commit();
    }

    public void deleteCashIncomeEntry(CashIncomeEntry entry) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.remove(entry);
        trans.commit();
    }

    public Collection<CashIncomeEntry> findCashIncomeEntryByYear(int year) {

        Query q = em.createNativeQuery("SELECT * FROM CashIncomeEntry WHERE YEAR(date)= :date", CashIncomeEntry.class);
        q.setParameter("date", year);
        Collection<CashIncomeEntry> result = (List<CashIncomeEntry>) q.getResultList();
        result.forEach(r -> System.out.println(r.toString()));
        return result;
    }

    public Collection<CashIncomeEntry> findCashIncomeEntryByYearAndMonth(int year, int month) {

        Query q = em.createNativeQuery("SELECT * FROM CashIncomeEntry WHERE YEAR(date)= :year AND MONTH(date)= :month", CashIncomeEntry.class);
        q.setParameter("year", year);
        q.setParameter("month", month);
        Collection<CashIncomeEntry> result = (List<CashIncomeEntry>) q.getResultList();
        return result;
    }
//    CASH OUTCOME ENTRY CREATE - EDIT - DELETE - FIND_BY_DATE

    public void createCashOutcomeEntry(Date date, String desc, Double amount) {
        CashOutcomeEntry entry = new CashOutcomeEntry(date, desc, amount);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(entry);
        trans.commit();
    }

    public void updateCashOutcomeEntry(CashOutcomeEntry entry, Date date, String desc, Double amount) {
        EntityTransaction trans = em.getTransaction();
        entry.setDate(date);
        entry.setDescription(desc);
        entry.setAmount(amount);
        em.persist(entry);
        trans.commit();
    }

    public void deleteCashOutcomeEntry(CashOutcomeEntry entry) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.remove(entry);
        trans.commit();
    }

    public Collection<CashOutcomeEntry> findCashOutcomeEntryByYear(int year) {

        Query q = em.createNativeQuery("SELECT * FROM CashOutcomeEntry WHERE YEAR(date)= :date", CashOutcomeEntry.class);
        q.setParameter("date", year);
        Collection<CashOutcomeEntry> result = (List<CashOutcomeEntry>) q.getResultList();
        result.forEach(r -> System.out.println(r.toString()));
        return result;
    }

    public Collection<CashOutcomeEntry> findCashOutcomeEntryByYearAndMonth(int year, int month) {

        Query q = em.createNativeQuery("SELECT * FROM CashOutcomeEntry WHERE YEAR(date)= :year AND MONTH(date)= :month", BankIncomeEntry.class);
        q.setParameter("year", year);
        q.setParameter("month", month);
        Collection<CashOutcomeEntry> result = (List<CashOutcomeEntry>) q.getResultList();
        result.forEach(r -> System.out.println(r.toString()));
        return result;
    }
}
