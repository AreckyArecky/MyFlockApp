package com.myflock.myflockapp.auth;

/**
 *
 * @author DevelopmentMPOS
 */
public class UserLogged {

    private static UserLogged logged = null;
    public static User user;

    private UserLogged(User user) {
        UserLogged.user = user;
    }

    public static UserLogged getInstance(User user) {
        if (logged == null) {
            logged = new UserLogged(user);

        }
        return logged;
    }

    public static String getUserName() {
        return UserLogged.user.getUsername();
    }

    public static void logout() {
        logged = null;
    }
}
