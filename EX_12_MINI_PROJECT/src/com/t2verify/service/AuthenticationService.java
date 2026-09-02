package com.t2verify.service;

import com.t2verify.dao.UserDAO;
import com.t2verify.model.User;
import com.t2verify.util.HashUtil;
import com.t2verify.util.UserSession;
import com.t2verify.util.ValidationUtil;

import java.sql.SQLException;

public class AuthenticationService {

    private final UserDAO userDAO = new UserDAO();

    public User authenticate(String usernameOrEmail, String password) throws Exception {
        if (!ValidationUtil.isNotEmpty(usernameOrEmail) || !ValidationUtil.isNotEmpty(password)) {
            throw new IllegalArgumentException("Username/email and password cannot be empty.");
        }

        User user = userDAO.findByUsernameOrEmail(usernameOrEmail.trim());
        if (user == null) {
            throw new IllegalArgumentException("Invalid username/email or password.");
        }

        String hashedPassword = HashUtil.hashString(password);
        if (!user.getPasswordHash().equals(hashedPassword)) {
            throw new IllegalArgumentException("Invalid username/email or password.");
        }

        UserSession.setCurrentUser(user);
        return user;
    }

    public boolean registerUser(String fullName, String email, String username, String password, String confirmPassword) throws Exception {
        if (!ValidationUtil.isNotEmpty(fullName)) {
            throw new IllegalArgumentException("Full name is required.");
        }
        if (!ValidationUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
        if (!ValidationUtil.isValidUsername(username)) {
            throw new IllegalArgumentException("Username must be 3-50 alphanumeric characters or underscores.");
        }
        if (!ValidationUtil.isNotEmpty(password) || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirm password do not match.");
        }

        if (userDAO.findByUsername(username.trim()) != null) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (userDAO.findByEmail(email.trim()) != null) {
            throw new IllegalArgumentException("Email address is already registered.");
        }

        String hashedPassword = HashUtil.hashString(password);
        User newUser = new User(fullName.trim(), email.trim().toLowerCase(), username.trim(), hashedPassword, "USER");
        return userDAO.registerUser(newUser);
    }
}
