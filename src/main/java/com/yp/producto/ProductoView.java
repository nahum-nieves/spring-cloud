package com.yp.producto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class ProductoView {
    private Integer id;
    private String name;
    private String year;
    private String color;

    public ProductoView(Producto producto) {
        BeanUtils.copyProperties(producto,this);
    }
}
