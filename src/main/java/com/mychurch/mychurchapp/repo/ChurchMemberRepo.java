/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mychurch.mychurchapp.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.mychurch.mychurchapp.entity.ChurchMember;
import jakarta.persistence.EntityTransaction;

/**
 *
 * @author arkadiuszkuzma
 */
public class ChurchMemberRepo {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em = factory.createEntityManager();

    public void createMember(String firstName, String secondName, int age) {
        ChurchMember newMember = new ChurchMember(firstName, secondName, age);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(newMember);

        System.out.println(" New Guest ID " + newMember.getId());

        trans.commit();
    }
}
