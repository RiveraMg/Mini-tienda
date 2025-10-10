/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.mini.tiendahu2.domain;

/**
 *
 * @author Coder
 */
public class Food extends Product{

    public Food(String name, double price) {
        super(name, price);
    }

    @Override
    public String getDescription() {
        return "Product type: Food, it is suitable for human consumption,";
    }

}


