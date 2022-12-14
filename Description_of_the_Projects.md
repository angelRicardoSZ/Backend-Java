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

- Entity: Here the tables are mapped as classes.  

  table "products"

  ```java
  package com.example.demo.persistence.entity;
  
  import javax.persistence.*;
  
  @Entity    // a class of type Entity indicates a class that, at an abstract level, is correlated with a table in the database. An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
  @Table(name = "productos")  // tells JPA which table to map an entity against
  public class Producto {
  
      @Id  // simple primary key
      @GeneratedValue(strategy = GenerationType.IDENTITY) // java generate automatic id
      @Column(name = "id_producto")  //  it will allow us to define very important aspects about the columns of the database of the database such as the name, length, constrains, etc.
      private Integer idProducto;
  
      private String nombre;
  
      @Column(name = "id_categoria")
      private Integer idCategoria;
  
      @Column(name = "codigo_barras")
      private String codigoBarras;
  
      @Column(name = "precio_venta")
      private Double precioVenta;
  
      @Column(name = "cantidad_stock")
      private Integer cantidadStock;
  
      private Boolean estado;
  
      @ManyToOne 
      @JoinColumn(name="id_categoria", insertable = false, updatable = false)
      private Categoria categoria;
  
      public Integer getIdProducto() {
          return idProducto;
      }
  
      public void setIdProducto(Integer idProducto) {
          this.idProducto = idProducto;
      }
  
      public String getNombre() {
          return nombre;
      }
  
      public void setNombre(String nombre) {
          this.nombre = nombre;
      }
  
      public Integer getIdCategoria() {
          return idCategoria;
      }
  
      public void setIdCategoria(Integer idCategoria) {
          this.idCategoria = idCategoria;
      }
  
      public Categoria getCategoria() {
          return categoria;
      }
  
      public void setCategoria(Categoria categoria) {
          this.categoria = categoria;
      }
  
      public String getCodigoBarras() {
          return codigoBarras;
      }
  
      public void setCodigoBarras(String codigoBarras) {
          this.codigoBarras = codigoBarras;
      }
  
      public Double getPrecioVenta() {
          return precioVenta;
      }
  
      public void setPrecioVenta(Double precioVenta) {
          this.precioVenta = precioVenta;
      }
  
      public Integer getCantidadStock() {
          return cantidadStock;
      }
  
      public void setCantidadStock(Integer cantidadStock) {
          this.cantidadStock = cantidadStock;
      }
  
      public Boolean getEstado() {
          return estado;
      }
  
      public void setEstado(Boolean estado) {
          this.estado = estado;
      }
  }
  
  ```

  table "Categoria"

  ```java
  public class Categoria {
      @Id
      @Column(name = "id_categoria")
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer idCategoria;
  
      private String descripcion;
  
      private Boolean estado;
  
  
      @OneToMany(mappedBy = "categoria")
      private List<Producto> productos;
  
      public Integer getIdCategoria() {
          return idCategoria;
      }
  
      public void setIdCategoria(Integer idCategoria) {
          this.idCategoria = idCategoria;
      }
  
      public String getDescripcion() {
          return descripcion;
      }
  
      public void setDescripcion(String descripcion) {
          this.descripcion = descripcion;
      }
  
      public Boolean getEstado() {
          return estado;
      }
  
      public void setEstado(Boolean estado) {
          this.estado = estado;
      }
  
  
      public List<Producto> getProductos() {
          return productos;
      }
  
      public void setProductos(List<Producto> productos) {
          this.productos = productos;
      }
  }
  ```

  table = "Compras"

  ```java
  package com.example.demo.persistence.entity;
  
  import javax.persistence.*;
  import java.time.LocalDateTime;
  import java.util.List;
  
  
  @Entity
  @Table(name = "compras")
  public class Compra {
  
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id_compra")
      private Integer idCompra;
  
      @Column(name = "id_cliente")
      private String idCliente;
  
      private LocalDateTime fecha;
  
      @Column(name = "medio_pago")
      private String medioPago;
  
      private String comentario;
  
      private String estado;
  
  
  
      @ManyToOne
      @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
      private Cliente cliente;
  
      @OneToMany(mappedBy="producto")
      private List<ComprasProducto> productos;
  
      public Integer getIdCompra() {
          return idCompra;
      }
  
      public void setIdCompra(Integer idCompra) {
          this.idCompra = idCompra;
      }
  
      public String getIdCliente() {
          return idCliente;
      }
  
      public void setIdCliente(String idCliente) {
          this.idCliente = idCliente;
      }
  
      public LocalDateTime getFecha() {
          return fecha;
      }
  
      public void setFecha(LocalDateTime fecha) {
          this.fecha = fecha;
      }
  
      public String getMedioPago() {
          return medioPago;
      }
  
      public void setMedioPago(String medioPago) {
          this.medioPago = medioPago;
      }
  
      public String getComentario() {
          return comentario;
      }
  
      public void setComentario(String comentario) {
          this.comentario = comentario;
      }
  
      public String getEstado() {
          return estado;
      }
  
      public void setEstado(String estado) {
          this.estado = estado;
      }
  }
  ```
  
  table="Cliente"
  
  ```java
  package com.example.demo.persistence.entity;
  
  import javax.persistence.*;
  import java.util.List;
  
  @Entity
  @Table(name = "clientes")
  public class Cliente {
  
      @Id
      private String id;
  
      private String nombre;
  
      private String apellidos;
  
      private Long celular;
  
      private String direccion;
  
      private String correo_electronico;
  
      @OneToMany(mappedBy = "cliente")
      private List<Compra> compras;
  
  
      public String getId() {
          return id;
      }
  
      public void setId(String id) {
          this.id = id;
      }
  
      public String getNombre() {
          return nombre;
      }
  
      public void setNombre(String nombre) {
          this.nombre = nombre;
      }
  
      public String getApellidos() {
          return apellidos;
      }
  
      public void setApellidos(String apellidos) {
          this.apellidos = apellidos;
      }
  
      public Long getCelular() {
          return celular;
      }
  
      public void setCelular(Long celular) {
          this.celular = celular;
      }
  
      public String getDireccion() {
          return direccion;
      }
  
      public void setDireccion(String direccion) {
          this.direccion = direccion;
      }
  
      public String getCorreo_electronico() {
          return correo_electronico;
      }
  
      public void setCorreo_electronico(String correo_electronico) {
          this.correo_electronico = correo_electronico;
      }
  }
  ```
  
  **table with compose primary key**
  
- table = "compras_productos"

  ```java
  package com.example.demo.persistence.entity;
  
  import javax.persistence.*;
  
  @Entity
  @Table(name = "compras_productos")
  public class ComprasProducto {
  
      @EmbeddedId  // compound key
      private ComprasProductoPK id;
  
      private Integer cantidad;
  
      private Double total;
  
      private Boolean estado;
  
      @ManyToOne
      @JoinColumn(name = "id_compra", insertable = false, updatable = false)
      private Compra compra;
  
      @ManyToOne
      @JoinColumn(name = "id_producto", insertable = false, updatable = false)
      private Producto producto;
  
      public ComprasProductoPK getId() {
          return id;
      }
  
      public void setId(ComprasProductoPK id) {
          this.id = id;
      }
  
      public Integer getCantidad() {
          return cantidad;
      }
  
      public void setCantidad(Integer cantidad) {
          this.cantidad = cantidad;
      }
  
      public Double getTotal() {
          return total;
      }
  
      public void setTotal(Double total) {
          this.total = total;
      }
  
      public Boolean getEstado() {
          return estado;
      }
  
      public void setEstado(Boolean estado) {
          this.estado = estado;
      }
  }
  
  ```

  When you have a table in which your primary key is composed, you must make a separate class that contains the attributes that make up the key

  ```java
  package com.example.demo.persistence.entity;
  
  import javax.persistence.Column;
  import javax.persistence.Embeddable;
  import java.io.Serializable;
  
  @Embeddable  //Defines a class whose instances are stored as an intrinsic part of an owning entity and share the identity of the entity. Each of the persistent properties or fields of the embedded object is mapped to the database table for the entity.
  public class ComprasProductoPK  implements Serializable {
  
      @Column(name="id_compra")
      private Integer idCompra;
  
      @Column(name="id_producto")
      private Integer idProducto;
  
      public Integer getIdCompra() {
          return idCompra;
      }
  
      public void setIdCompra(Integer idCompra) {
          this.idCompra = idCompra;
      }
  
      public Integer getIdProducto() {
          return idProducto;
      }
  
      public void setIdProducto(Integer idProducto) {
          this.idProducto = idProducto;
      }
  }
  
  ```

  





 

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

