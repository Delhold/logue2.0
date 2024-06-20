package com.elvis.login.logueo.services;

import com.elvis.login.logueo.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    //Implemntamos un método para obtener el producto por
    //id
    Optional<Producto> porId(Integer id);


}
