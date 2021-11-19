package com.yp.producto;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface ProductoRepository extends Repository<Producto,Integer> {
    Producto save(Producto entity);
    List<Producto> findAll();
}
