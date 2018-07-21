package com.bolsadeideas.springboot.backend.apirest.models.services;


import com.bolsadeideas.springboot.backend.apirest.models.entity.Factura;

import java.util.List;

public interface IFacturaservice {

    List<Factura> findAll();
}
