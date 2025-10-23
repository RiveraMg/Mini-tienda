/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mini.store.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Melanie Rivera
 */
public class Config {
    
    private final Properties props = new Properties();

    public Config() {
        try (InputStream in = getClass().getResourceAsStream("/db.properties")) {
            if (in == null) {
                throw new IllegalStateException("db.properties not found");
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration", e);
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}
