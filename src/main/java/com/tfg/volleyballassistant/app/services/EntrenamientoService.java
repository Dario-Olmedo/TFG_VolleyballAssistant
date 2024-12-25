package com.tfg.volleyballassistant.app.services;

import com.tfg.volleyballassistant.app.model.Entrenamiento;
import com.tfg.volleyballassistant.app.repository.EntrenamientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenamientoService {

    private final EntrenamientoRepository entrenamientoRepository;

    public EntrenamientoService(EntrenamientoRepository entrenamientoRepository) {
        this.entrenamientoRepository = entrenamientoRepository;
    }

    // Listar entrenamientos por categoría
    public List<Entrenamiento> listarEntrenamientosPorCategoria(Entrenamiento.Categoria categoria) {
        return entrenamientoRepository.findByCategoria(categoria);
    }

    // Guardar un nuevo entrenamiento
    public Entrenamiento guardarEntrenamiento(Entrenamiento entrenamiento) {
        return entrenamientoRepository.save(entrenamiento);
    }

    // Buscar un entrenamiento por ID
    public Optional<Entrenamiento> buscarEntrenamientoPorId(Long id) {
        return entrenamientoRepository.findById(id);
    }

    // Eliminar un entrenamiento por ID
    public void eliminarEntrenamiento(Long id) {
        entrenamientoRepository.deleteById(id);
    }
}
