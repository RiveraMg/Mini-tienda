/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mini.store.service;

import com.mycompany.mini.store.domain.Product;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Coder
 */
public interface IProductService {
    void addProduct(Product product);
    Optional<Product> getProductById(int id);
    Optional<Product> getProductByName(String name);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int id);
    void deleteProductName(String name);
}
