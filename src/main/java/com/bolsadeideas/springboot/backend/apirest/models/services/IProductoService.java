package com.bolsadeideas.springboot.backend.apirest.models.services;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> findAll();
}
