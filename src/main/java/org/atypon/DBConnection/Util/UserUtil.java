package org.atypon.DBConnection.Util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atypon.DBConnection.models.Roles;
import org.atypon.DBConnection.models.User;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class UserUtil {
    private ObjectMapper objectMapper;
    private InputStream inputStream;
    private File dataFile;
    private TypeReference<List<User>> typeReference;
    private List<User> users;

    public UserUtil() throws IOException {
        this.objectMapper = new ObjectMapper();
        this.dataFile = new File(APIPath.users());
        this.usersInit();
    }

    private void usersInit() throws IOException {
        if (dataFile.exists()) {
            this.inputStream = new FileInputStream(this.dataFile);
            this.typeReference = new TypeReference<List<User>>() {
            };
            this.users = this.objectMapper.readValue(this.inputStream, this.typeReference);
        } else {
            this.users = new LinkedList<User>();
        }
    }

    public boolean addUser(User user) {
        if (this.isExist(user)) {
            return false;
        } else {
            try {
                users.add(user);
                this.updateUsersData();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean createFirstUser(User user) {
        users.clear();
       return addUser(user);
    }

    private void updateUsersData() throws IOException {
        this.objectMapper.writeValue(dataFile, users);
    }

    public boolean isExist(User user) {
        return users.contains(user);
    }

    public boolean deleteUser(User user) {
        if (this.isExist(user)) {
            try {
                users.remove(user);
                this.updateUsersData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("user is not exist");
            return false;
        }
    }

    public void updateUser(User oldUser, User newUser) throws IOException {
        int index = users.indexOf(oldUser);
        users.set(index, newUser);
        System.out.println("User Successfully Updated");
        updateUsersData();
    }

    public void setRole(User user, Roles role) {
        if (isExist(user)) {
            int index = users.indexOf(user);
            users.get(index).setRole(role);
        }
    }
}
