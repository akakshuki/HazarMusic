package helper;

import java.util.HashSet;
import java.util.Set;

public final class UserSession {

    private static UserSession instance;

    private String userName;
    private Set<Entities.User> User;

    private UserSession(String userName, Set<Entities.User> User) {
        this.userName = userName;
        this.User = User;
    }

    public static UserSession getInstace(String userName,Set<Entities.User> User) {
        if(instance == null) {
            instance = new UserSession(userName, User);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public Set<Entities.User> getUser() {
        return User;
    }

    public void cleanUserSession() {
        userName = "";// or null
        User = new HashSet<>();// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + User +
                '}';
    }
}