package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.ProductosCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductosCategoriaRepository extends JpaRepository<ProductosCategoria, Long> {

    @Query("SELECT pc FROM ProductosCategoria pc WHERE pc.claveCategoria=:claveCategoria")
    Optional<ProductosCategoria> findProductosCategoriaByClave(@Param("claveCategoria") String claveCategoria);

    @Query("SELECT pc FROM ProductosCategoria pc WHERE pc.activo = true")
    List<ProductosCategoria> findProductosCategoriaByActivo();

}
