/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.mini.tienda.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Coder
 */
public class InventoryService {

    private ArrayList<String> products = new ArrayList<>(); // Create an ArrayList object
    private double[] prices = new double[0]; //Create Array for price
    private HashMap<String, Integer> quantity = new HashMap<>(); //Create a HashMap to stock for product
    private double totalSales = 0;

    // method to expand array of price
    private double[] expandPrices(double[] oldPrices, double newPrice) {
        double[] newPrices = new double[oldPrices.length + 1];
        System.arraycopy(oldPrices, 0, newPrices, 0, oldPrices.length);
        newPrices[oldPrices.length] = newPrice;
        return newPrices;
    }

    // method to returns the index of a product by name
    private int indexOfName(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // method add product
    public String addProduct(String name, double price, int qty) {
        if (name == null || name.trim().isEmpty()) return "Name product invalid, please enter name product valid";
        if (products.contains(name)) return "The product you are trying to enter already exists.";
        if (price <= 0 || qty < 0) return "Price or Quantity invalid, this data must be greater than to 0";

        products.add(name);
        prices = expandPrices(prices, price);
        quantity.put(name, qty);

        return "Product successfully added";
    }

    // method to list inventary
    public String listInventory() {
        if (products.isEmpty()) return "There are no products in inventory, add a product and it will be displayed here";

        StringBuilder sb = new StringBuilder("CURRENT INVENTORY:\n\n");
        for (int i = 0; i < products.size(); i++) {
            String name = products.get(i);
            sb.append((i + 1)).append(". ")
              .append(name)
              .append(" - Price: $").append(prices[i])
              .append(" - Quantity: ").append(quantity.get(name))
              .append(" units\n");
        }
        return sb.toString(); 
    }

    // metod that buy a product
    public String buyProduct(String name, int qty) {
        int index = indexOfName(name);
        if (index == -1) return "The product you are trying to purchase does not exist.";
        if (qty <= 0) return "The quantity of the product must be greater than 0";

        int available = quantity.get(name);
        if (qty > available) {
            return "We are sorry, this currently exceeds the quantity of products we have available. " + available + " units available";
        }
   

        double total = prices[index] * qty;
        totalSales += total;
        quantity.put(name, available - qty);

        return "Your purchase was successful. With a total of: $" + total;
    }

    // show ranking tha product chepper and more expensive
    public String showStatistics() {
        if (products.isEmpty()) return "There is no visible ranking because there are no registered products";

        double min = prices[0], max = prices[0];
        String minProd = products.get(0), maxProd = products.get(0);

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
                minProd = products.get(i);
            }
            if (prices[i] > max) {
                max = prices[i];
                maxProd = products.get(i);
            }
        }

        return "RANKING:\n\nCheaper: " + minProd + " ($" + min + ")\nMore expensive: " + maxProd + " ($" + max + ")";
    }

    // search product for name
    public String searchProduct(String term) {
        if (products.isEmpty()) return "There are no registered producers to search for";
        if (term == null || term.trim().isEmpty()) return "The search is empty";

        StringBuilder sb = new StringBuilder("Search results:\n\n");
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).toLowerCase().contains(term.toLowerCase())) {
                sb.append(products.get(i))
                  .append(" - $").append(prices[i])
                  .append(" - Quantity: ").append(quantity.get(products.get(i)))
                  .append(" Units\n");
            }
        }

        return sb.length() > 30 ? sb.toString() : "No matches found for that search.";
    }

    // show ticket final
    public String showTicket() {
        return "Total purchases made: $" + totalSales;
    }
}


