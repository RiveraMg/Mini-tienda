/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.mini.tiendahu2.domain;

/**
 *
 * @author Coder
 */
public class Appliances extends Product {
     public Appliances(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Product type: household appliance, can be used for household chores, work, studies, personal use or other functions.";
    }

}
