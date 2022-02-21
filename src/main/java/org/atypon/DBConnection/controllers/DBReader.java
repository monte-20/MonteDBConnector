package org.atypon.DBConnection.controllers;

import org.atypon.DBConnection.services.ReadService;
import org.atypon.DBConnection.services.UserService;

import java.util.List;
import java.util.Map;

public class DBReader {

private final ReadService SERVICE =new ReadService();

    public Map<String,Object> readFile(String path) throws Exception {
        if(UserService.userServiceInstance().isActive())
        return SERVICE.readFile(path);
        System.out.println("You must login first");
        throw new Exception();
    }

    public List<Map<String, Object>> readCollection(String path) throws Exception {
        if(UserService.userServiceInstance().isActive())
            return SERVICE.readCollection(path);
        System.out.println("You must login first");
        throw new Exception();
    }
}
