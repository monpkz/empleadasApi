package ar.com.ada.api.empleadas.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import ar.com.ada.api.empleadas.entities.*;
import ar.com.ada.api.empleadas.models.response.GenericResponse;
import ar.com.ada.api.empleadas.services.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping("/categorias") // Ningun web method devuelve un void
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {

        GenericResponse respuesta = new GenericResponse();

        service.crearCategoria(categoria);

        respuesta.isOk = true;
        respuesta.id = categoria.getCategoriaId();
        respuesta.message = "La categoria fue creada con exito.";

        return ResponseEntity.ok(respuesta);
    }

    // GET /categorias
    @GetMapping("/categorias") // hacer el mapping
    public ResponseEntity<List<Categoria>> traerCategorias() {// return Response Entity
        return ResponseEntity.ok(service.traerCategorias()); // return entity con el valor esperado

    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<GenericResponse> eliminar(@PathVariable Integer id){

        Categoria categoria = service.buscarCategoria(id);
        service.eliminar(id);
        GenericResponse respuesta = new GenericResponse();

        respuesta.isOk = true;
        respuesta.id = categoria.getCategoriaId();
        respuesta.message = "Categoria eliminada con exito.";

        return ResponseEntity.ok(respuesta);
    }

    //Patrones de diseño: strategy
    @GetMapping("/categorias/sueldos-nuevos")
    public ResponseEntity<List<Empleada>> calcularProximosSueldos() {
        return ResponseEntity.ok(service.calcularProximosSueldos());
    }

    @GetMapping("/categorias/sueldos-actuales")
    public ResponseEntity<List<Empleada>> obtenerSueldosActuales() {
        return ResponseEntity.ok(service.obtenerSueldosActuales());
    }

    @GetMapping("/categorias/sin-empleadas")
    public ResponseEntity<List<Categoria>> obtenerCategoriasSinEmpleadas() {
        return ResponseEntity.ok(service.obtenerCategoriasSinEmpleadas());
    }

    @GetMapping("/categorias/minimo-sueldo")
    public ResponseEntity<Categoria> obtenerCategoriaConMinimoSueldo() {
        return ResponseEntity.ok(service.obtenerCategoriaConMinimoSueldo());
    }

    @GetMapping("/categorias/nombres")
    public ResponseEntity<List<String>> obtenerNombresCategorias() {
        return ResponseEntity.ok(service.obtenerNombresCategorias());
    }


}

