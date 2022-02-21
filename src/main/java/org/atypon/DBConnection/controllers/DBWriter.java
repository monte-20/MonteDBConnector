package org.atypon.DBConnection.controllers;

import org.atypon.DBConnection.models.User;
import org.atypon.DBConnection.services.UserService;
import org.atypon.DBConnection.services.WriteService;

import java.io.IOException;
import java.util.Map;

public class DBWriter {

    public void write(User user, String path, Map data) throws IOException {
        UserService userService = UserService.userServiceInstance();
        if (userService.login(user)) {
            WriteService writeService = new WriteService();
            writeService.write(path, data);
        } else {
            System.out.println("INVALID USER");
        }
    }
}
