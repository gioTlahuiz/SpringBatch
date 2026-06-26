package com.academia.batch.controller;

import com.academia.batch.repository.EstudianteEntity;
import com.academia.batch.repository.EstudianteRepository;
import com.academia.batch.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes") //url que necesitaremos para consumir la API
public class EstudianteController {

    private final EstudianteRepository repository;
    private final EstudianteService service;

    //constructor
    public EstudianteController(EstudianteRepository repository, EstudianteService service) {
        this.repository = repository;
        this.service = service;
    }

    //añadimos el método get
    @GetMapping
    public List<EstudianteEntity> listar (){
        return repository.findAll();
    }

    //método get para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteEntity> obtener(@PathVariable Long id){
        return repository.findById(id)
                .map(ResponseEntity::ok)//metodo reference para el status ok
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/estudiantes/aprobados/total -> dato calculado por el servicio
    @GetMapping("/aprobados/total")
    public Map<String, Long> totalAprobados() {
        return Map.of("aprobados", service.contarAprobados());
    }

    // POST /api/estudiantes -> crea; 201 Created para añadir nuevos registros
    @PostMapping
    public ResponseEntity<EstudianteEntity> crear(@RequestBody EstudianteEntity nuevo) {
        EstudianteEntity guardado = repository.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);   // 201
    }

    //ahora añadimos el metodo put para reemplazar registros
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteEntity> actualizar(@PathVariable Long id,
                                                       @RequestBody EstudianteEntity datos) {
        return repository.findById(id)
                .map(e -> {
                    e.setNombre(datos.getNombre());
                    e.setGrupo(datos.getGrupo());
                    e.setNota1(datos.getNota1());
                    e.setNota2(datos.getNota2());
                    e.setNota3(datos.getNota3());
                    e.setPromedio(datos.getPromedio());
                    return ResponseEntity.ok(repository.save(e));   // 200
                })
                .orElse(ResponseEntity.notFound().build());          // 404
    }

    //metodo PATCH para alterar parcialmente un registro
    @PatchMapping("/{id}")
    public ResponseEntity<EstudianteEntity> cambiarGrupo(@PathVariable Long id,
                                                         @RequestBody Map<String, String> cambios) {
        return repository.findById(id)
                .map(e -> {
                    if (cambios.containsKey("grupo")) {
                        e.setGrupo(cambios.get("grupo"));
                    }
                    return ResponseEntity.ok(repository.save(e));   // 200
                })
                .orElse(ResponseEntity.notFound().build());          // 404
    }

    //finalmente añadimos el metodo DELETE para eliminar registros por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();                // 404
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();                   // 204 No Content
    }

}
