/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministrore.hu4.services.impl;

import com.codeup.ministrore.hu4.DAO.IProductDAO;
import com.codeup.ministrore.hu4.DAO.impl.ProductDAOImpl;
import com.codeup.ministrore.hu4.domain.Product;
import com.codeup.ministrore.hu4.exceptions.InvalidDataException;
import com.codeup.ministrore.hu4.exceptions.DuplicateException;
import com.codeup.ministrore.hu4.exceptions.PersistenceException;
import com.codeup.ministrore.hu4.services.IProductService;
import java.util.List;

/**
 *
 * @author Coder
 */
public class ProductServiceImpl implements IProductService {

    private final IProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl();
    }

    @Override
    public void addProduct(Product product) throws InvalidDataException, DuplicateException, PersistenceException {
        // Validaciones
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new InvalidDataException("The product name cannot be empty.");
        }
        if (product.getPrice() <= 0) {
            throw new InvalidDataException("The product price must be greater than zero.");
        }
        if (product.getStock() < 0) {
            throw new InvalidDataException("The product stock cannot be negative.");
        }

        // Validar duplicado
        Product existing = productDAO.findByName(product.getName());
        if (existing != null) {
            throw new DuplicateException("A product with this name already exists.");
        }

        // Guardar producto
        productDAO.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() throws PersistenceException {
        return productDAO.getAllProducts();
    }

    @Override
    public Product findByName(String name) throws PersistenceException {
        return productDAO.findByName(name);
    }

    @Override
    public void updatePrice(String name, double newPrice) throws InvalidDataException, PersistenceException {
        if (newPrice <= 0) {
            throw new InvalidDataException("The new price must be greater than zero.");
        }
        productDAO.updatePrice(name, newPrice);
    }

    @Override
    public void updateStock(String name, int newStock) throws InvalidDataException, PersistenceException {
        if (newStock < 0) {
            throw new InvalidDataException("The new stock cannot be negative.");
        }
        productDAO.updateStock(name, newStock);
    }

    @Override
    public void deleteProduct(String name) throws PersistenceException {
        productDAO.deleteProduct(name);
    }
}
