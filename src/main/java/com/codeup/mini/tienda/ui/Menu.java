/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.mini.tienda.ui;

import com.codeup.mini.tienda.service.InventoryService;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */
public class Menu {
    private InventoryService service = new InventoryService(); // Instancia del servicio

    public void menu() {
        boolean running = true;
        
        JOptionPane.showMessageDialog(null, "Welcome manage inventory");

        while (running) {
            String option = JOptionPane.showInputDialog(
                "Choose the Option (1-7)\n" +
                "1. Add product\n" +
                "2. List inventory\n" +
                "3. Buy product\n" +
                "4. Ranking\n" +
                "5. Search product\n" +
                "6. Invoice\n" +
                "7. Exit\n"
            );


            try {
                switch (option) {
                    case "1": // Add product
                        String name = JOptionPane.showInputDialog("Name product:");
                        if (name == null) break;

                        double price = Double.parseDouble(JOptionPane.showInputDialog("Price:"));
                        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity:"));

                        String resultAdd = service.addProduct(name, price, quantity);
                        JOptionPane.showMessageDialog(null, resultAdd);
                        break;

                    case "2": // List inventory
                        String inventory = service.listInventory();
                        JOptionPane.showMessageDialog(null, inventory);
                        break;

                    case "3": // Buy product
                        String productBuy = JOptionPane.showInputDialog("Name product buy:");
                        if (productBuy == null) break;

                        int qtyBuy = Integer.parseInt(JOptionPane.showInputDialog("Quantity:"));
                        String resultBuy = service.buyProduct(productBuy, qtyBuy);
                        JOptionPane.showMessageDialog(null, resultBuy);
                        break;

                    case "4": // Ranking
                        String stats = service.showStatistics();
                        JOptionPane.showMessageDialog(null, stats);
                        break;

                    case "5": // search product
                        String searchTerm = JOptionPane.showInputDialog("Name product search:");
                        if (searchTerm == null) break;

                        String searchResult = service.searchProduct(searchTerm);
                        JOptionPane.showMessageDialog(null, searchResult);
                        break;

                    case "6": // Invoice or total product 
                        String invoice = service.showTicket();
                        JOptionPane.showMessageDialog(null, invoice);
                        break;

                    case "7": // exit
                        JOptionPane.showMessageDialog(null, "Thanks you very much, see you soon!");
                        running = false;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Option not valid, please enter a valid option");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: please enter a valid numerical option");
            }
        }
    }
}



