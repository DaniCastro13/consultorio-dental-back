package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {

    @Query("SELECT p FROM Productos p WHERE p.codigoBarras=:codigoBarras OR p.claveProducto=:claveProducto OR p.nombreProducto=:nombreProducto")
    Optional<Productos> findByProduct(@Param("codigoBarras") String codigoBarras, @Param("claveProducto") String claveProducto, @Param("nombreProducto") String nombreProducto);

    @Query("SELECT p FROM Productos p WHERE p.activo=true")
    List<Productos> findAllProductsActives();
}
