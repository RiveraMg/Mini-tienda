/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mini.store.ui;

import com.mycompany.mini.store.domain.Product;
import com.mycompany.mini.store.service.IProductService;
import com.mycompany.mini.store.service.impl.ProductServiceImpl;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class MiniStoreUI {

    private final IProductService productService;
    private int addCount = 0;
    private int updateCount = 0;
    private int deleteCount = 0;

    public MiniStoreUI() {
        this.productService = new ProductServiceImpl();
    }

    public void showMenu() {
        while (true) {
            String option = JOptionPane.showInputDialog(
                    "🛒 MINI STORE MEL 🛒\n"
                            + "1. Add product\n"
                            + "2. List inventory\n"
                            + "3. Update price\n"
                            + "4. Update stock\n"
                            + "5. Delete product\n"
                            + "6. Search by name\n"
                            + "7. Exit and show summary\n"
                            + "Choose an option:"
            );

            if (option == null) return; // Si el usuario cierra la ventana

            switch (option) {
                case "1" -> addProduct();
                case "2" -> listProducts();
                case "3" -> updatePrice();
                case "4" -> updateStock();
                case "5" -> deleteProduct();
                case "6" -> searchProduct();
                case "7" -> {
                    exitSummary();
                    return; // salir del programa
                }
                default -> JOptionPane.showMessageDialog(null, "❌ Invalid option!");
            }
        }
    }

    private void addProduct() {
        String name = JOptionPane.showInputDialog("Enter product name:");
        if (name == null || name.trim().isEmpty()) return;

        String priceStr = JOptionPane.showInputDialog("Enter product price:");
        if (priceStr == null || priceStr.trim().isEmpty()) return;

        String stockStr = JOptionPane.showInputDialog("Enter product stock:");
        if (stockStr == null || stockStr.trim().isEmpty()) return;

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);
            Product product = new Product(0, name, price, stock);
            productService.addProduct(product);
            addCount++;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid number format!");
        }
    }

    private void listProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products found.");
            return;
        }

        StringBuilder list = new StringBuilder("INVENTORY LIST\n\n");
        for (Product p : products) {
            list.append("ID: ").append(p.getId())
                    .append(" | Name: ").append(p.getName())
                    .append(" | Price: ").append(p.getPrice())
                    .append(" | Stock: ").append(p.getStock())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, list.toString());
    }

    private void updatePrice() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter product ID:");
            if (idStr == null || idStr.trim().isEmpty()) return;

            int id = Integer.parseInt(idStr);
            String newPriceStr = JOptionPane.showInputDialog("Enter new price:");
            if (newPriceStr == null || newPriceStr.trim().isEmpty()) return;

            double newPrice = Double.parseDouble(newPriceStr);

            Optional<Product> productOpt = productService.getProductById(id);
            if (productOpt.isPresent()) {
                Product p = productOpt.get();
                p.setPrice(newPrice);
                productService.updateProduct(p);
                updateCount++;
                JOptionPane.showMessageDialog(null, "✅ Price updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Product not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid number format!");
        }
    }

    private void updateStock() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter product ID:");
            if (idStr == null || idStr.trim().isEmpty()) return;

            int id = Integer.parseInt(idStr);
            String newStockStr = JOptionPane.showInputDialog("Enter new stock:");
            if (newStockStr == null || newStockStr.trim().isEmpty()) return;

            int newStock = Integer.parseInt(newStockStr);

            Optional<Product> productOpt = productService.getProductById(id);
            if (productOpt.isPresent()) {
                Product p = productOpt.get();
                p.setStock(newStock);
                productService.updateProduct(p);
                updateCount++;
                JOptionPane.showMessageDialog(null, "✅ Stock updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Product not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid number format!");
        }
    }

    private void deleteProduct() {
        String deleteName = JOptionPane.showInputDialog("Enter product name to delete:");
        if (deleteName == null || deleteName.trim().isEmpty()) return;

        productService.deleteProductName(deleteName);
        deleteCount++;
    }

    private void searchProduct() {
        String searchName = JOptionPane.showInputDialog("Enter product name:");
        if (searchName == null || searchName.trim().isEmpty()) return;

        Optional<Product> found = productService.getProductByName(searchName);
        if (found.isPresent()) {
            Product p = found.get();
            JOptionPane.showMessageDialog(null,
                    "PRODUCT FOUND\n\n"
                            + "ID: " + p.getId()
                            + "\nName: " + p.getName()
                            + "\nPrice: " + p.getPrice()
                            + "\nStock: " + p.getStock());
        } else {
            JOptionPane.showMessageDialog(null, "❌ Product not found.");
        }
    }

    private void exitSummary() {
        JOptionPane.showMessageDialog(null,
                "SESSION SUMMARY\n"
                        + "Products added: " + addCount + "\n"
                        + "Products updated: " + updateCount + "\n"
                        + "Products deleted: " + deleteCount + "\n\n"
                        + "👋 Thank you for visiting our store!");
        System.exit(0);
    }
}
