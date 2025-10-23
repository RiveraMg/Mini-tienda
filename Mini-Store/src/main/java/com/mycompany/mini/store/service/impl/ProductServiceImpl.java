/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mini.store.service.impl;

import com.mycompany.mini.store.DAO.impl.ProductRepositoryImpl;
import com.mycompany.mini.store.domain.Product;
import com.mycompany.mini.store.service.IProductService;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */

public class ProductServiceImpl implements IProductService {

    private final ProductRepositoryImpl productRepo;

    public ProductServiceImpl() {
        this.productRepo = new ProductRepositoryImpl();
    }

    @Override
    public void addProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            JOptionPane.showMessageDialog(null, "Product name cannot be empty");
            return;
        }

        if (product.getPrice() <= 0) {
            JOptionPane.showMessageDialog(null, "Product price must be greater than zero");
            return;
        }

        productRepo.create(product);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepo.findById(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        productRepo.update(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepo.delete(id);
    }
    
    @Override
    public void deleteProductName(String name) {
        productRepo.deleteByName(name);
    }
}

