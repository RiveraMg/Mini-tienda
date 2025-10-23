/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codeup.ministrore.hu4.DAO;

import com.codeup.ministrore.hu4.domain.Product;
import com.codeup.ministrore.hu4.exceptions.PersistenceException;
import java.util.List;

/**
 *
 * @author Coder
 */
public interface IProductDAO {

    void addProduct(Product product) throws PersistenceException;

    List<Product> getAllProducts() throws PersistenceException;

    Product findByName(String name) throws PersistenceException;

    void updatePrice(String name, double newPrice) throws PersistenceException;

    void updateStock(String name, int newStock) throws PersistenceException;

    void deleteProduct(String name) throws PersistenceException;
}
