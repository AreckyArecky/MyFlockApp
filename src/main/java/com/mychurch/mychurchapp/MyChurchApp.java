package com.mychurch.mychurchapp;

import javafx.application.Application;
import com.mychurch.mychurchapp.auth.repo.UserRepo;
import java.util.List;
import com.mychurch.mychurchapp.repo.ChurchMemberRepo;
import com.mychurch.mychurchapp.entity.ChurchMember;
import java.util.Scanner;
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

        Scanner scanner = new Scanner(System.in);

        Application.launch(LoginView.class);

    }
}
