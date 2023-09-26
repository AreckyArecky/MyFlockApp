/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mychurch.mychurchapp.auth;

import jakarta.persistence.*;
import com.mychurch.mychurchapp.auth.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

/**
 *
 * @author DevelopmentMPOS
 */
public class UserRepo {

    private static EntityManagerFactory emfAuth = Persistence.createEntityManagerFactory("authPersistenceUnit");
    private static EntityManager emAuth = emfAuth.createEntityManager();

    public void createUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            User newUser = new User(username, password);
            EntityTransaction trans = emAuth.getTransaction();
            trans.begin();
            emAuth.persist(newUser);
            System.out.println("New User ID: " + newUser.getId());
            trans.commit();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }

    public User findById(int id) {
        emAuth.clear();
        return emAuth.find(User.class, id);
    }

    public void deleteUser(User user) {
        EntityTransaction trans = emAuth.getTransaction();
        trans.begin();
        emAuth.remove(user);
        trans.commit();
    }

    public boolean auth(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        EntityTransaction trans = emAuth.getTransaction();
        trans.begin();
        CriteriaBuilder cb = emAuth.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("username"), username));

        Query query = emAuth.createQuery(cr);
        query.setMaxResults(1);

        List<User> resultList = query.getResultList();
        User result = resultList.get(0);
        trans.commit();
        return PassHash.validatePassword(password, result.getPassword());
    }
}
