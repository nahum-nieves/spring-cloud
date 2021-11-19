package com.yp.producto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity crearProducto(ProductoView nuevoProducto){
        productoService.crearProducto(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pokemons updated");
    }

    @GetMapping
    public ResponseEntity<List<ProductoView>> buscarProductos(){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarProductos());
    }
}

