package org.atypon.DBConnection.controllers;

import org.atypon.DBConnection.models.Roles;
import org.atypon.DBConnection.models.User;
import org.atypon.DBConnection.services.UserService;

import java.io.IOException;

public class UserController {

    private static UserService service = UserService.userServiceInstance();

    public static boolean login(User user) {

        return service.login(user);
    }

    public static boolean signUp() {
        return service.signUp();
    }

    public static boolean addUser(User user) {
        return service.addUser(user);
    }

    public static void updateInfo(User user) {
        try {
            service.updateCurrentUser(user);
            System.out.println(service.getCurrentUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserRole(User user, Roles role) {
            service.setUserRole(user,role);
    }

    public static boolean removeUser(User user) {
        return service.deleteUser(user);
    }
}
