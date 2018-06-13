package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Factura;
import com.bolsadeideas.springboot.backend.apirest.models.entity.ItemFactura;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.xml.ws.RequestWrapper;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ResController {


    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }


    @GetMapping("/clientes/{id}")
    public Cliente show(@PathVariable Long id) {
        return this.clienteService.findOne(id);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {

        cliente.setCreateAt(new Date());
        this.clienteService.save(cliente);
        return cliente;
    }

    @PutMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
        Cliente currentCliente = this.clienteService.findOne(id);
        currentCliente.setNombre(cliente.getNombre());
        currentCliente.setApellido(cliente.getApellido());
        currentCliente.setEmail(cliente.getEmail());
        this.clienteService.save(currentCliente);
        return currentCliente;
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Cliente currentCliente = this.clienteService.findOne(id);
        this.clienteService.delete(currentCliente);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity<Factura> updateWithMultipleObjects (@RequestBody Factura factura) {

        System.out.println("Total " + factura.getTotal());
       // Double ttl = factura.setTotal(factura.getTotal());
        clienteService.saveFactura(factura);

        return new ResponseEntity<Factura>(factura, HttpStatus.OK);
    }

    @GetMapping(value = "/factura/cargar-productos/{term}")
    public List<Producto> cargarProductos(@PathVariable String term) {
        System.out.println("Buscamos producto" + " : " + term);
        List<Producto> productos = clienteService.findByNombre(term);
        for(Producto p : productos){
            System.out.println(p.getNombre());
        }
        return productos;
    }

    @GetMapping(value = "/cargar-clientes/{term}")
    public List<Cliente> cargarClientes(@PathVariable String term) {

        System.out.println("Buscamos producto" + " : " + term);
        List<Cliente> cliente = clienteService.findClienteByNombre(term);

        return cliente;
    }


}
