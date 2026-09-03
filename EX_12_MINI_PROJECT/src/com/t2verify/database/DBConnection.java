package com.t2verify.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_DB_NAME = "T2Verify_db";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found in classpath!");
            e.printStackTrace();
        }
    }

    public static String getDbUser() {
        String user = System.getenv("DB_USER");
        if (user == null || user.trim().isEmpty()) {
            user = System.getProperty("db.user", DEFAULT_USER);
        }
        return user;
    }

    public static String getDbPassword() {
        String pass = System.getenv("DB_PASSWORD");
        if (pass == null || pass.trim().isEmpty()) {
            pass = System.getenv("DB_PASS");
        }
        if (pass == null || pass.trim().isEmpty()) {
            pass = System.getProperty("db.password");
        }
        if (pass == null || pass.trim().isEmpty()) {
            pass = System.getProperty("password");
        }
        if (pass != null && !pass.trim().isEmpty()) {
            return pass;
        }

        return DEFAULT_PASS;
    }

    public static String getDbUrl() {
        String url = System.getenv("DB_URL");
        if (url != null && !url.trim().isEmpty()) {
            return url;
        }
        String customUrl = System.getProperty("db.url");
        if (customUrl != null && !customUrl.trim().isEmpty()) {
            return customUrl;
        }
        return "jdbc:mysql://" + DEFAULT_HOST + ":" + DEFAULT_PORT + "/" + DEFAULT_DB_NAME
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }

    public static Connection getConnection() throws SQLException {
        ensureDatabaseExists();
        return DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword());
    }

    /**
     * Checks if the target database exists; if not, attempts to connect to MySQL server root and create it.
     */
    private static synchronized void ensureDatabaseExists() {
        String baseUrl = "jdbc:mysql://" + DEFAULT_HOST + ":" + DEFAULT_PORT
                + "/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        try (Connection rootConn = DriverManager.getConnection(baseUrl, getDbUser(), getDbPassword());
             Statement stmt = rootConn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DEFAULT_DB_NAME);
        } catch (SQLException e) {
            // Ignore if user lacks admin grants or database already exists
        }
    }
}
