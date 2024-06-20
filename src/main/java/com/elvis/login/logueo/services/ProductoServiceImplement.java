package com.elvis.login.logueo.services;

import com.elvis.login.logueo.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImplement implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList();
    }

    @Override
    public Optional<Producto> porId(Integer id) {
        return listar().stream().filter(p->p.getIdProducto().equals(id)).findAny();
    }


}


