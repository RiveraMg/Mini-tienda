/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministrore.hu4.DAO.impl;

import com.codeup.ministrore.hu4.DAO.IProductDAO;
import com.codeup.ministrore.hu4.connection.ConnectionFactory;
import com.codeup.ministrore.hu4.domain.Product;
import com.codeup.ministrore.hu4.exceptions.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coder
 */

public class ProductDAOImpl implements IProductDAO {

    @Override
    public void addProduct(Product product) throws PersistenceException {
        String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getStock());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException("Error inserting product: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getAllProducts() throws PersistenceException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                products.add(p);
            }

        } catch (SQLException e) {
            throw new PersistenceException("Error fetching products: " + e.getMessage(), e);
        }
        return products;
    }

    @Override
    public Product findByName(String name) throws PersistenceException {
        String sql = "SELECT * FROM products WHERE name = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new PersistenceException("Error finding product: " + e.getMessage(), e);
        }
    }

    @Override
    public void updatePrice(String name, double newPrice) throws PersistenceException {
        String sql = "UPDATE products SET price = ? WHERE name = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newPrice);
            stmt.setString(2, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException("Error updating price: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateStock(String name, int newStock) throws PersistenceException {
        String sql = "UPDATE products SET stock = ? WHERE name = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newStock);
            stmt.setString(2, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException("Error updating stock: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteProduct(String name) throws PersistenceException {
        String sql = "DELETE FROM products WHERE name = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException("Error deleting product: " + e.getMessage(), e);
        }
    }
}
