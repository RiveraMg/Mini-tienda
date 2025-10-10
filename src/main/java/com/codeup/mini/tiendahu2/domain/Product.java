/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.mini.tiendahu2.domain;

/**
 *
 * @author Coder
 */
public abstract class Product {

    private String name;
    private double price;

    //constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Método abstracto (debe implementarse en las subclases)
    public abstract String getDescription();
}

