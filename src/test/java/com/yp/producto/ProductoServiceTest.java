package com.yp.producto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {




    @InjectMocks private ProductoService productoService;
    @Mock private ProductoRepository productoRepository;

    @Test
    public void testListOfElements(){
        when(productoRepository.findAll()).thenReturn(productosListDummy());
        List<ProductoView> productoViews = productoService.buscarProductos();
        verify(productoRepository,times(1)).findAll();
        assertEquals(2,productoViews.size());
    }



    private List<Producto> productosListDummy(){
        List<Producto> productos = new ArrayList<>();
        Producto product1 = Producto.builder()
                .id(1).color("blue")
                .name("product1").year("1992").build();
        Producto product2 = Producto.builder()
                .id(1).color("red")
                .name("product2").year("1993").build();
        productos.add(product1);
        productos.add(product2);
        return productos;

    }
}
