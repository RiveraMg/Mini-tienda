# 🏪 MiniStore – Layered Architecture with Custom Exceptions

## 📘 Overview

**MiniStore** is a Java console-based mini application that simulates a simple store inventory management system.  
It implements a **Layered Architecture (UI, Service, DAO, Domain, Config, Exception)** with a clean separation of concerns and **custom exception handling** for robust error control.

This project is an evolution of the previous assignment (“MiniStore with Interfaces + JDBC”), now enhanced with:
- A multi-layered design.
- Custom exception management.
- Proper use of JDBC with `PreparedStatement` and `try-with-resources`.
- Input validation and user-friendly error feedback via `JOptionPane`.

---

## 🎯 Objective

To reinforce clean code principles, modular architecture, and effective error handling by structuring the application into separate layers with well-defined responsibilities.

---

## 🧩 Architecture Layers

### 1. **Domain Layer (`domain/`)**
Contains the entity classes that represent real-world objects.  
- **`Product`** → Represents a product in the inventory.
  ```java
  private int id;
  private String name;
  private double price;
  private int stock;


2. DAO Layer (dao/)
Responsible for database communication through JDBC.
ProductDAO → Interface defining CRUD methods.
ProductDAOImpl → Implements all database operations (Create, Read, Update, Delete) using PreparedStatement.
Translates SQL errors into PersistenciaException.

3. Service Layer (service/)
Contains business logic and validation rules.
ProductService → Interface defining service operations.
ProductServiceImpl → Validates data and delegates to DAO.
Throws custom exceptions like DatoInvalidoException and DuplicadoException.

4. UI Layer (ui/)

Handles user interaction via JOptionPane.
MiniStoreUI → Manages menu options and input collection.
Calls the service layer for operations.
Catches exceptions and displays messages without stopping the program.

5. Config Layer (config/)
Manages the database connection and configuration.
Config → Loads db.properties file.
ConnectionFactory → Provides JDBC connection instance using MySQL.

6. Exceptions Layer (exception/)
Defines custom exception classes used throughout the system:
DatoInvalidoException → Invalid or empty data.
DuplicadoException → Product name already exists.
PersistenciaException → Database or connection error.

## Sructure project
 ```

MiniStore/
│
├── src/
│   ├── config/
│   │   └── Config.java
│   │
├── connection/
│   │   └── ConnectionFactory.java
│   │
│   ├── domain/
│   │   └── Product.java
│   │
│   ├── dao/
│   │   ├── IProductDAO.java
│   │   └── impl/
│   │       └── ProductDAOImpl.java
│   │  
│   ├── db/
│   │   └── SQLtemplate
│   │      
│   │
│   ├── service/
│   │   ├── ProductService.java
│   │   └── impl/
│   │       └── ProductServiceImpl.java
│   │
│   ├── ui/
│   │   └── MiniStoreUI.java
│   │
│   ├── exception/
│   │   ├── DatoInvalidoException.java
│   │   ├── DuplicadoException.java
│   │   └── PersistenciaException.java
│   │
│   └── Main.java
│
└── resources/
    └── config.properties

 ```
## ⚙️ Database Configuration

 ```

CREATE DATABASE MiniStore;
USE MiniStore;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);

 ```

**db.properties**
Located in the /resources directory.

 ```
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:tupuerto/tunombredelabasededatos
db.user=youruser
db.password=yourpassword

 ```
## 🖥️ Application Flow

The UI Layer (MiniStoreUI) shows a menu using JOptionPane:
-Add product

-List inventory

-Update price

-Update stock

-Delete product

-Search product by name

-Exit and show summary

The Service Layer validates input and delegates operations to the DAO.
The DAO Layer executes the SQL operations using JDBC.
Custom exceptions are thrown and handled gracefully with meaningful messages.

## 🧠 Key Features

✅ Layered architecture (UI, Service, DAO, Domain)

✅ Custom exception handling

✅ JDBC with PreparedStatement and try-with-resources

✅ Input validation and duplication checks

✅ Friendly error messages via JOptionPane

✅ Summary of successful operations at program exit

## 👨‍💻 Author

Melanie Rivera
MiniStore Project – Semana 4
