package com.bolsadeideas.springboot.backend.apirest.models.services;


import com.bolsadeideas.springboot.backend.apirest.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacturaServiceImpl implements IFacturaservice{

    @Autowired
    IFacturaDao facturaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findAll() {
        return (List<Factura>) facturaDao.findAll();
    }
}
