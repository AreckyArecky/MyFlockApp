/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mychurch.mychurchapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mychurch.mychurchapp.entity.ChurchMember;
import com.mychurch.mychurchapp.util.HibernateUtil;

/**
 *
 * @author arkadiuszkuzma
 */
public class ChurchMemberDao {

    public void saveMember(ChurchMember member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(member);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public ChurchMember getMemberByID(int ID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ChurchMember) session.get(ChurchMember.class, ID);
        }
    }
    public ChurchMember getMemberByName(String FirstName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ChurchMember) session.get(ChurchMember.class, FirstName);
        }
    }

    public List<ChurchMember> getMembers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ChurchMember", ChurchMember.class).list();
        }
    }
}
