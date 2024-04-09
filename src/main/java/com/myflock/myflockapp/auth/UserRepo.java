/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myflock.myflockapp.auth;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DevelopmentMPOS
 */
public class UserRepo {

    private static EntityManagerFactory emfAuth = Persistence.createEntityManagerFactory("authPersistenceUnit");
    private static EntityManager emAuth = emfAuth.createEntityManager();

    public void createUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            User newUser = new User(username, password, false);
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
        if (!resultList.isEmpty()) {
            User result = resultList.get(0);
            trans.commit();
            UserLogged.getInstance(result);

            return PassHash.validatePassword(password, result.getPassword());
        } else {
            trans.rollback();
            return false;

        }

    }

    public void updateUser(User user, String username, String password, boolean isAdmin) {
        try {
            EntityTransaction trans = emAuth.getTransaction();
            trans.begin();
            user.setUsername(username);
            user.setPassword(password);
            user.setIsAdmin(isAdmin);
            trans.commit();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUserNoPass(User user, String username, boolean isAdmin) {

        EntityTransaction trans = emAuth.getTransaction();
        trans.begin();
        user.setUsername(username);
        user.setIsAdmin(isAdmin);
        trans.commit();

    }

    public Collection<User> getAllUsers() {
        Query query = emAuth.createQuery("SELECT u FROM User u");
        return (Collection<User>) query.getResultList();
    }
}
