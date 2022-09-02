package com.example.demo.persistence.crud;

import com.example.demo.persistence.entity.Producto;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    // T: Domain type that repository manages (Generally the Entity/Model class name)
    // ID: Type of the id of the entity that repository manages (Generally the wrapper class of your @Id that is created inside the Entity/Model class)
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true) with spring data jpa
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
