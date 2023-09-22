package com.mychurch.mychurchapp;

import com.mychurch.mychurchapp.auth.repo.UserRepo;
import java.util.List;
import com.mychurch.mychurchapp.repo.ChurchMemberRepo;
import com.mychurch.mychurchapp.entity.ChurchMember;
import java.util.Scanner;

/**
 *
 * @author Areckyy_
 */
public class MyChurchApp {

    public static void main(String[] args) {
        ChurchMemberRepo memberRepo = new ChurchMemberRepo();
        memberRepo.createMember("Arkadiusz", "Kowalski", 20);

        Scanner scanner = new Scanner(System.in);

        UserRepo userRepo = new UserRepo();
        userRepo.createUser("admin", "pass123");
//        while (true) {
//            System.out.println("Give username and password: ");
//            String username = scanner.nextLine();
//            String password = scanner.nextLine();
//            System.out.println("");
//            String auth = userRepo.auth(username, password) ? "Login successfull." : "Wrong password!";
//            System.out.println(auth);
//            System.out.println("Finish? Y/N");
//            String end = scanner.nextLine();
//            if (end.equals("Y")) {
//                break;
//            } else {
//                continue;
//            }
//        }

    }
}
