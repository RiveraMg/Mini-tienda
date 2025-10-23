/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codeup.ministrore.hu4.services;

import com.codeup.ministrore.hu4.domain.Product;
import com.codeup.ministrore.hu4.exceptions.InvalidDataException;
import com.codeup.ministrore.hu4.exceptions.DuplicateException;
import com.codeup.ministrore.hu4.exceptions.PersistenceException;
import java.util.List;

/**
 *
 * @author Coder
 */
public interface IProductService {

    void addProduct(Product product) throws InvalidDataException, DuplicateException, PersistenceException;

    List<Product> getAllProducts() throws PersistenceException;

    Product findByName(String name) throws PersistenceException;

    void updatePrice(String name, double newPrice) throws InvalidDataException, PersistenceException;

    void updateStock(String name, int newStock) throws InvalidDataException, PersistenceException;

    void deleteProduct(String name) throws PersistenceException;
}
