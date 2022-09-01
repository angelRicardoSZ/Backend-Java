# Description of projects 

In this repository you will find different personal projects developed in Java SE courses.  The objective of this document is to describe the structure of these projects, their most important functions and the results obtained.

## 1.- Market Project

The project architecture is domain oriented ([Domain Drive Desing](https://medium.com/@jonathanloscalzo/domain-driven-design-principios-beneficios-y-elementos-primera-parte-aad90f30aa35)), the project is divided into the following layers:

**Domain:** 

- DTO & domain objects: application context. 

  The domain objects corresponds to Category and Product classes:

  

  Produc.Java

  ```java
  package com.example.demo.domain;
  
  public class Product {
      private int productId;
      private String name;
      private int categoryId;
      private double price;
      private int stock;
      private boolean active;
      private Category category;
  
      public int getProductId() {
          return productId;
      }
  
      public void setProductId(int productId) {
          this.productId = productId;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public int getCategoryId() {
          return categoryId;
      }
  
      public void setCategoryId(int categoryId) {
          this.categoryId = categoryId;
      }
  
      public double getPrice() {
          return price;
      }
  
      public void setPrice(double price) {
          this.price = price;
      }
  
      public int getStock() {
          return stock;
      }
  
      public void setStock(int stock) {
          this.stock = stock;
      }
  
      public boolean isActive() {
          return active;
      }
  
      public void setActive(boolean active) {
          this.active = active;
      }
  
      public Category getCategory() {
          return category;
      }
  
      public void setCategory(Category category) {
          this.category = category;
      }
  }
  ```

  Category.java

  ```java
  package com.example.demo.domain;
  
  public class Category {
      private int categoryId;
      private String category;
      private boolean active;
  
      public int getCategoryId() {
          return categoryId;
      }
  
      public void setCategoryId(int categoryId) {
          this.categoryId = categoryId;
      }
  
      public String getCategory() {
          return category;
      }
  
      public void setCategory(String category) {
          this.category = category;
      }
  
      public boolean isActive() {
          return active;
      }
  
      public void setActive(boolean active) {
          this.active = active;
      }
  }
  ```

- Services: Bridge between the controllers (ProductController) and the persistence layer (ProductRepository)

  **ProductController.java**

  ```java
  package com.example.demo.web.controller;
  
  // Domain layer
  import com.example.demo.domain.Product;
  import com.example.demo.domain.service.ProductService;
  
  // Spring framework
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RestController;
  
  // Utils
  import java.util.List;
  import java.util.Optional;
  
  @RestController
  @RequestMapping("/products")
  public class ProductController {
  
      @Autowired
      private ProductService productService;
  
      public List<Product> getAll() {
          return productService.getAll();
      }
  
      public Optional<Product> getProduct(int productId){
          return productService.getProduct(productId);
      }
  
      public Optional<List<Product>> getByCategory(int categoryId){
          return productService.getByCategory(categoryId);
      }
  
      public Product save(Product product){
          return productService.save(product);
      }
  
      public boolean delete(int productId){
          return productService.delete(productId);
      }
  
  }
  ```

  **ProductService.java**

  ```java
  package com.example.demo.domain.service;
  
  // Domain layer
  import com.example.demo.domain.Product;
  import com.example.demo.domain.repository.ProductRepository;
  
  // Spring framework
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  
  // utils
  import java.util.List;
  import java.util.Optional;
  
  @Service
  public class ProductService {
      @Autowired
      private ProductRepository productRepository;
  
      public List<Product> getAll(){
          return productRepository.getAll();
      }
  
      public Optional<Product> getProduct(int productId){
          return productRepository.getProduct(productId);
      }
  
      public Optional<List<Product>> getByCategory(int categoryId){
          return productRepository.getByCategory(categoryId);
      }
  
      public Product save(Product product)
      {
          return productRepository.save(product);
      }
  
      public boolean delete(int productId)
      {
          return getProduct(productId).map(product -> {
              productRepository.delete(productId);
              return true;
          }).orElse(false);
      }
  }
  ```

  **ProductRepository.java**

  ```java
  package com.example.demo.domain.repository;
  
  // Domain layer
  import com.example.demo.domain.Product;
  
  // utils
  import java.util.List;
  import java.util.Optional;
  
  public interface ProductRepository {
      List<Product> getAll();
  
      Optional<List<Product>> getByCategory(int categoryId);
  
      Optional<List<Product>> getScarseProducts(int quantity);
  
      Optional<Product> getProduct(int productId);
  
      Product save (Product product);
  
      void delete(int productId);
  
  
  }
  ```

- Repository specifications: Interfaces that determine the rules that persistence must comply with in order to act between the domain objects and the DB.

**Web**

- API Rest controllers

**Persistence**

- Entities 
- Repositories

**DATA BASE**

The project is connected to a DB in POSTGRESQL, the DB has the following tables:

*CATEGORIAS*

A category has many products

*PRODUCTOS*

A product has many shopping

*CLIENTES*

A client has many shopping

*COMPRAS*

A purchase has many products

*COMPRAS_PRODUCTOS* 

In this table it is found as foreign keys: id_compra and id_producto

The DB is generated with the **schema.sql** file and **data.sql**

