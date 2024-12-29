package com.tfg.volleyballassistant.app.repository;

import com.tfg.volleyballassistant.app.model.Entrenamiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    // Buscar entrenamientos por categoría
    List<Entrenamiento> findByCategoria(Entrenamiento.Categoria categoria);

    // Buscar entrenamientos por título o descripción dentro de una categoría
    List<Entrenamiento> findByCategoriaAndTituloContainingOrDescripcionContaining(Entrenamiento.Categoria categoria, String titulo, String descripcion);

    // Buscar entrenamientos por categoría con paginación
    Page<Entrenamiento> findByCategoria(Entrenamiento.Categoria categoria, Pageable pageable);
}