/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.mini.tiendahu2.service;

import com.codeup.mini.tiendahu2.domain.Appliances;
import com.codeup.mini.tiendahu2.domain.Food;
import com.codeup.mini.tiendahu2.domain.Product;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */
public class StoreService {
    
    private ArrayList<Product> products;             // List of products
    private HashMap<String, Integer> stock;          // Stock to name
    private double totalPurchases;                    // Total to purchases

    public StoreService() {
        products = new ArrayList<>();
        stock = new HashMap<>();
        totalPurchases = 0.0;
    }

    // Add product
    public void addProduct(String type, String name, double price, int quantity) {
        if (type == null || type.isEmpty() || name == null || name.isEmpty() || price <= 0 || quantity <= 0) {
            JOptionPane.showMessageDialog(null, "The data you are entering is invalid, remember that all fields must be filled and that the price and quantity must be greater than 0");
            return;
        }

        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "This product has already been added or a product with this name exists.");
                return;
            }
        }

        Product New;
        if (type.equalsIgnoreCase("Food")) {
            New = new Food(name, price);
        } else {
            New = new Appliances(name, price);
        }

        products.add(New);
        stock.put(name, quantity);
        JOptionPane.showMessageDialog(null, "The product was added successfully");
    }

    // list inventory
    public void listProducts() {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no products currently registered, add one to view them here.");
            return;
        }

        StringBuilder list = new StringBuilder("Stock of products:\n\n");
        for (Product p : products) {
            int quantity = stock.get(p.getName());
            list.append("Name product: ").append(p.getName())
                 .append("\nPrice: $").append(p.getPrice())
                 .append("\nStock: ").append(quantity)
                 .append("\nDescriptión: ").append(p.getDescription())
                 .append("\n\n");
        }
        JOptionPane.showMessageDialog(null, list.toString());
    }

    // Search to product for coincident
    public void searchProduct(String name) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "This field is required, please enter the name of the product you want to search for.");
            return;
        }

        StringBuilder results = new StringBuilder("Search results:\n\n");
        boolean found = false;

        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                int quantity = stock.get(p.getName());
                results.append("Name product: ").append(p.getName())
                         .append("\nPrice: $").append(p.getPrice())
                         .append("\nStock: ").append(quantity)
                         .append("\nDescriptión: ").append(p.getDescription())
                         .append("\n\n");
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "The product you were looking for was not found.");
        } else {
            JOptionPane.showMessageDialog(null, results.toString());
        }
    }

    // buy product
    public void buyProduct(String name, int quantity) {
        if (!stock.containsKey(name)) {
            JOptionPane.showMessageDialog(null, "This product is not registered in our store.");
            return;
        }

        int available = stock.get(name);
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "The quantity you entered is not valid, remember that the product's stock value must be greater than 0");
            return;
        }

        if (quantity > available) {
            JOptionPane.showMessageDialog(null, "There are not enough units for this product.");
            return;
        }

        // Update stock and total of purchases
        stock.put(name, available - quantity);
        double price = 0;

        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                price = p.getPrice();
                break;
            }
        }

        double subtotal = price * quantity;
        totalPurchases += subtotal;

        JOptionPane.showMessageDialog(null, "Purchase made successfully.\nSubtotal: $" + subtotal);
    }

    // Ranking: cheapest product and more expensive
    public void showRanking() {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are currently no products in inventory, enter one to view products");
            return;
        }

        Product moreExpensive = products.get(0);
        Product cheapest = products.get(0);

        for (Product p : products) {
            if (p.getPrice() > moreExpensive.getPrice()) moreExpensive = p;
            if (p.getPrice() < cheapest.getPrice()) cheapest = p;
        }

        String message = "Most expensive product: " + moreExpensive.getName() + " ($" + moreExpensive.getPrice() + ")\n" +
                         "Cheapest product: " + cheapest.getName() + " ($" + cheapest.getPrice() + ")";
        JOptionPane.showMessageDialog(null, message);
    }

    // Show final ticket
    public void ShowFinalTicket() {
        JOptionPane.showMessageDialog(null, "Total purchases made: $" + totalPurchases);
    }
}

