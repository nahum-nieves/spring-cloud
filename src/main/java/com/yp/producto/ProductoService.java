package com.yp.producto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public void crearProducto(ProductoView nuevoProducto) {
        productoRepository.save(deserializarProducto(nuevoProducto));
    }

    public List<ProductoView> buscarProductos(){
        return productoRepository.findAll().stream().map(producto -> new ProductoView(producto))
                .collect(Collectors.toList());
    }


    private Producto deserializarProducto(ProductoView productoView){
        Producto producto = new Producto();
        BeanUtils.copyProperties(productoView,producto);
        return producto;
    }
}
