# 🛒 Mini-Store Application

## 📋 Overview
**Mini-Store** is a simple Java-based desktop application that simulates a small store management system.  
It allows users to **add**, **list**, **update**, **delete**, and **search** products using graphical dialogs powered by **Swing (JOptionPane)**.  
The system also provides a session summary when the user exits the application.

---

## 🚀 Features
- ➕ **Add Product:** Register a new product with name, price, and stock.  
- 📦 **List Inventory:** View all registered products.  
- 💲 **Update Price:** Modify the price of an existing product.  
- 🔄 **Update Stock:** Change the available stock for a product.  
- ❌ **Delete Product:** Remove a product by name.  
- 🔍 **Search Product:** Find a product by its name.  
- 📊 **Session Summary:** Display the number of added, updated, and deleted products when exiting.  

---

## 🧠 Technologies Used
- **Java SE 17+**
- **Maven** (project management)
- **Swing (JOptionPane)** for graphical interface
- **OOP principles** (Encapsulation, Interfaces, and Services)

---

## 🧩 Project Structure
```

Mini-Store/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/mycompany/mini/store/
│       │       ├── MiniStoreUI.java
│       │       ├── Product.java
│       │       ├── IProductService.java
│       │       ├── ProductServiceImpl.java
│       │       └── Main.java
│       └── resources/
│           └── db.properties
├── pom.xml
└── README.md

```


---

## ⚙️ How to Run
1. Open the project in **NetBeans**, **IntelliJ IDEA**, or any Java IDE.
2. Make sure **Maven** dependencies are properly loaded.
3. Run the project by executing the `Main.java` class (which contains the `public static void main(String[] args)` method).
4. The menu will appear through dialog boxes — simply follow the on-screen instructions.

---

## 📌 Example Workflow
1. Select **“Add Product”** and enter the product details.  
2. Choose **“List Inventory”** to see the stored products.  
3. Update, delete, or search products as needed.  
4. Exit using option **“7”** to view the session summary.  

---

## 🧾 Session Summary Example
SESSION SUMMARY
Products added: 3
Products updated: 2
Products deleted: 1

👋 Thank you for visiting our store!
