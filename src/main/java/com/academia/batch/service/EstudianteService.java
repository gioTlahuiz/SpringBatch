package com.academia.batch.service;

import com.academia.batch.repository.EstudianteEntity;
import com.academia.batch.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {
    private final EstudianteRepository repository;

    //hacemos la inyeccion por medio del constructor
    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    //implementamos el conteo de alumnos con promedio aprobatorio >=70
    public long contarAprobados() {
        return repository.findAll().stream()
                .filter(e -> e.getPromedio() >= 70)
                .count();
    }
}
