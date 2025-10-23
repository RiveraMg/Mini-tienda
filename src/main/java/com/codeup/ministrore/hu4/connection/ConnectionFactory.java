/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministrore.hu4.connection;

import com.codeup.ministrore.hu4.config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */
public class ConnectionFactory {
    
    private static final Config config = new Config();
    private static final String URL = config.get("db.url");
    private static final String USER = config.get("db.user");
    private static final String PASSWORD = config.get("db.password");
    private static final String DRIVER = config.get("db.driver");

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Database driver not found: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error connecting to the database:\n" + e.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

