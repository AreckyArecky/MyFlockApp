package com.mychurch.mychurchapp;

import javafx.application.Application;
import com.mychurch.mychurchapp.auth.UserRepo;
import java.util.List;
import com.mychurch.mychurchapp.repo.ChurchMemberRepo;
import com.mychurch.mychurchapp.entity.ChurchMember;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.LoginView;
import view.MainView;

/**
 *
 * @author Areckyy_
 */
public class MyChurchApp {

    public static void main(String[] args) {
        ChurchMemberRepo memberRepo = new ChurchMemberRepo();
        memberRepo.createMember("Arkadiusz", "Kowalski", 20);

        UserRepo userRepo = new UserRepo();
        try {
            userRepo.createUser("admin", "pass123");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyChurchApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(MyChurchApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scanner scanner = new Scanner(System.in);

        Application.launch(LoginView.class);
    }
}
