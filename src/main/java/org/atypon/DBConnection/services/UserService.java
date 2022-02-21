package org.atypon.DBConnection.services;

import org.atypon.DBConnection.Util.UserUtil;
import org.atypon.DBConnection.models.Roles;
import org.atypon.DBConnection.models.User;

import java.io.IOException;

public class UserService {
    private static UserService serviceInstance = null;
    private User currentUser;
    private UserUtil util;
    private boolean active=false;

    private UserService() {
        try {
            util = new UserUtil();
            currentUser = new User();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return active;
    }

    public static UserService userServiceInstance() {
        if (serviceInstance == null)
            serviceInstance = new UserService();
        return serviceInstance;
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean login(User user) {
        if (isAuthenticated(user)) {
            currentUser = user;
            System.out.println("Logged in Successfully");
            active=true;
            return true;
        }
        System.out.println("Login Failed");
        return false;
    }

    public boolean signUp() {
       User newUser=new User();
        System.out.println("Welcome to the NO SQL database your first user info:");
        System.out.println(newUser);
        System.out.println("if you want to change your info call updateInfo(user)");
       return util.createFirstUser(newUser);
    }

    public boolean isAuthenticated(User user) {
        return util.isExist(user);
    }

    public void updateCurrentUser(User user) throws IOException {
        util.updateUser(currentUser, user);
        setCurrentUser(user);
    }

    public boolean addUser(User user) {
        if (currentUser.isAdmin()) {
            return util.addUser(user);
        }
        System.out.println("ERROR: CURRENT USER IS NOT AN ADMIN");
        return false;
    }

    public void setUserRole(User user, Roles role) {
        if (currentUser.isAdmin()) {
            util.setRole(user, role);
        }
        System.out.println("ERROR: CURRENT USER IS NOT AN ADMIN");
        return;
    }

    public boolean deleteUser(User user) {
        if (currentUser.isAdmin()) {
            return util.deleteUser(user);
        }
        System.out.println("ERROR: CURRENT USER IS NOT AN ADMIN");
        return false;
    }
}
