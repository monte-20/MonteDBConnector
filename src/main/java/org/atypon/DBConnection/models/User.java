package org.atypon.DBConnection.models;

import java.util.Objects;


public class User {

    private String username;
    private String password;
    private Roles role;
    private boolean admin;

    public User(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        setRole(role);
    }

    public User() {
        username = "admin";
        password = "admin";
        role = Roles.ADMIN;
        admin = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        if (role.equals(Roles.ADMIN)) {
            admin = true;
        } else {
            admin = false;
        }
        this.role = role;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return admin == user.admin && Objects.equals(username, user.username) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, admin);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}

