/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministrore.hu4.ui;

import com.codeup.ministrore.hu4.domain.Product;
import com.codeup.ministrore.hu4.exceptions.InvalidDataException;
import com.codeup.ministrore.hu4.exceptions.DuplicateException;
import com.codeup.ministrore.hu4.exceptions.PersistenceException;
import com.codeup.ministrore.hu4.services.IProductService;
import com.codeup.ministrore.hu4.services.impl.ProductServiceImpl;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */

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
                    null,
                    "MINI STORE MEL\n" +
                    "1. Add Product\n" +
                    "2. List Products\n" +
                    "3. Update Price\n" +
                    "4. Update Stock\n" +
                    "5. Delete Product\n" +
                    "6. Search by Name\n" +
                    "7. Exit and Show Summary\n\n" +
                    "Choose an option:",
                    "MiniStore Menu",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (option == null) break; // usuario canceló
            switch (option) {
                case "1" -> addProduct();
                case "2" -> listProducts();
                case "3" -> updatePrice();
                case "4" -> updateStock();
                case "5" -> deleteProduct();
                case "6" -> searchProduct();
                case "7" -> {
                    showSummary();
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }

    private void addProduct() {
        try {
            String name = JOptionPane.showInputDialog("Enter product name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter product stock:"));

            Product p = new Product(name, price, stock);
            productService.addProduct(p);
            addCount++;
            JOptionPane.showMessageDialog(null, "Product added successfully!");

        } catch (InvalidDataException | DuplicateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage());
        }
    }

    private void listProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No products available.");
                return;
            }
            StringBuilder sb = new StringBuilder("Product List\n");
            for (Product p : products) {
                sb.append(p.getName())
                  .append(" | Price: $").append(p.getPrice())
                  .append(" | Stock: ").append(p.getStock())
                  .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error loading products: " + e.getMessage());
        }
    }

    private void updatePrice() {
        try {
            String name = JOptionPane.showInputDialog("Enter product name to update:");
            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new price:"));
            productService.updatePrice(name, newPrice);
            updateCount++;
            JOptionPane.showMessageDialog(null, "Price updated successfully!");
        } catch (InvalidDataException | PersistenceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateStock() {
        try {
            String name = JOptionPane.showInputDialog("Enter product name to update:");
            int newStock = Integer.parseInt(JOptionPane.showInputDialog("Enter new stock:"));
            productService.updateStock(name, newStock);
            updateCount++;
            JOptionPane.showMessageDialog(null, "Stock updated successfully!");
        } catch (InvalidDataException | PersistenceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteProduct() {
        try {
            String name = JOptionPane.showInputDialog("Enter product name to delete:");
            productService.deleteProduct(name);
            deleteCount++;
            JOptionPane.showMessageDialog(null, "Product deleted successfully!");
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchProduct() {
        try {
            String name = JOptionPane.showInputDialog("Enter product name:");
            Product p = productService.findByName(name);
            if (p != null) {
                JOptionPane.showMessageDialog(null,
                        "Product found:\n" +
                        "Name: " + p.getName() + "\n" +
                        "Price: $" + p.getPrice() + "\n" +
                        "Stock: " + p.getStock());
            } else {
                JOptionPane.showMessageDialog(null, "Product not found.");
            }
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showSummary() {
        String summary = " OPERATION SUMMARY\n" +
                         "Products Added: " + addCount + "\n" +
                         "Products Updated: " + updateCount + "\n" +
                         "Products Deleted: " + deleteCount + "\n";
        JOptionPane.showMessageDialog(null, summary, "Summary", JOptionPane.INFORMATION_MESSAGE);
    }
}
