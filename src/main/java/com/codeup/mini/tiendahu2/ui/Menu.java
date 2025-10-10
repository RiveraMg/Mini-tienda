/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.mini.tiendahu2.ui;

import com.codeup.mini.tiendahu2.service.StoreService;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */
public class Menu {

    private final StoreService storeService = new StoreService();

    public void start() {
        JOptionPane.showMessageDialog(null, "Welcome Mini Store Mel");
        while (true) {
            String option = JOptionPane.showInputDialog("""
                CHOOSE THE OPTION (1 - 7)
                
                1. Add product
                2. List inventory
                3. Buy product
                4. Ranking
                5. Search product
                6. Invoice
                7. Exit
                """);

            if (option == null) break;

            switch (option) {
                case "1" -> addProduct();
                case "2" -> storeService.listProducts();
                case "3" -> buyProduct();
                case "4" -> storeService.showRanking();
                case "5" -> storeService.searchProduct(JOptionPane.showInputDialog("Nombre del producto:"));
                case "6" -> storeService.ShowFinalTicket();
                case "7" -> {
                    JOptionPane.showMessageDialog(null, "Thanks you very much, see you soon!");
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Option not valid, please enter a valid option");
            }
        }
    }

    private void addProduct() {
        try {
            String type = JOptionPane.showInputDialog("Enter the Product Type (Food or Appliances):");
            String name = JOptionPane.showInputDialog("Product name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Product price:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity:"));
            storeService.addProduct(type, name, price, quantity);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Please verify that the data entered is correct.");
        }
    }

    private void buyProduct() {
        try {
            String name = JOptionPane.showInputDialog("Name of the product to purchase:");
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity:"));
            storeService.buyProduct(name, quantity);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Please check that the data entered is correct and all fields are filled out.");
        }
    }
}



