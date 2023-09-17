package com.mychurch.mychurchapp;

import java.util.List;
import com.mychurch.mychurchapp.dao.ChurchMemberDao;
import com.mychurch.mychurchapp.entity.ChurchMember;

/**
 *
 * @author Areckyy_
 */
public class MyChurchApp {

    public static void main(String[] args) {
        ChurchMemberDao memberDao = new ChurchMemberDao();
        ChurchMember member = new ChurchMember("Arkadiusz", "Kuzma", 30);

        memberDao.saveMember(member);

        List<ChurchMember> members = memberDao.getMembers();
        members.forEach(s -> System.out.println(s.getFirstName()));
    }
}
