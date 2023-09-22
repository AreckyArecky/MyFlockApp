package com.mychurch.mychurchapp;

import java.util.List;
import com.mychurch.mychurchapp.repo.ChurchMemberRepo;
import com.mychurch.mychurchapp.entity.ChurchMember;

/**
 *
 * @author Areckyy_
 */
public class MyChurchApp {

    public static void main(String[] args) {
        ChurchMemberRepo memberRepo = new ChurchMemberRepo();
        memberRepo.createMember("Arkadiusz", "Sosna", 22);
    }
}
