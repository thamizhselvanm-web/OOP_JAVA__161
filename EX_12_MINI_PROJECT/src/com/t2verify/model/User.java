package com.t2verify.model;

import java.sql.Timestamp;

public class User {

    private int id;
    private String fullName;
    private String email;
    private String username;
    private String passwordHash;
    private String role; // 'ADMIN' or 'USER'
    private Timestamp createdAt;

    public User() {}

    public User(int id, String fullName, String email, String username, String passwordHash, String role, Timestamp createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User(String fullName, String email, String username, String passwordHash, String role) {
        this(0, fullName, email, username, passwordHash, role, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return fullName + " (" + username + ")";
    }
}
