/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mini.store.DAO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Melanie Rivera
 */
public interface IRepository<T> {
    void create(T t);
    Optional<T> findById(int id);
    Optional<T> findByName(String name);
    List<T> findAll();
    void update(T t);
    void delete(int id);
    void deleteByName(String name);
}

