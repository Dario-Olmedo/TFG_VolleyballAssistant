package com.tfg.volleyballassistant.app.repository;

import com.tfg.volleyballassistant.app.model.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
}