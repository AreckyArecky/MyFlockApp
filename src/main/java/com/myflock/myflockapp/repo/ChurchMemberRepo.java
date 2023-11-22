/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.myflock.myflockapp.entity.ChurchMember;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.Collection;

/**
 *
 * @author arkadiuszkuzma
 */
public class ChurchMemberRepo {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em = emf.createEntityManager();

    public void createMember(String firstName, String secondName, int age) {
        ChurchMember newMember = new ChurchMember(firstName, secondName, age);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(newMember);

        System.out.println(" New Guest ID " + newMember.getId());

        trans.commit();
    }

    public ChurchMember findById(int id) {
        em.clear();
        return em.find(ChurchMember.class, id);

    }

    public void updateAge(ChurchMember member, int age) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        member.setAge(age);
        trans.commit();
    }

    public void updateFirstName(ChurchMember member, String firstName) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        member.setFirstName(firstName);
        trans.commit();
    }

    public void updateSecondName(ChurchMember member, String secondName) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        member.setSecondName(secondName);
        trans.commit();
    }

    public void updateService(ChurchMember member, String service) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        member.setService(service);
        trans.commit();
    }

    public void updateIsMember(ChurchMember member, boolean isMember) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        member.setIsMember(isMember);
        trans.commit();

    }

    public void updatePhoneNumber(ChurchMember member, int phoneNumber) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        member.setPhoneNumber(phoneNumber);
        trans.commit();
    }

    public void deleteMember(ChurchMember member) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.remove(member);
        trans.commit();
    }

    public Collection<ChurchMember> getAllMembers() {
        Query query = em.createQuery("SELECT m FROM ChurchMember m");
        return (Collection<ChurchMember>) query.getResultList();
    }
}
