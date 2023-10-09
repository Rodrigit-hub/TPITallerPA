package com.app.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entidad.OrdenTrabajo; // Cambio Vehiculo por OrdenTrabajo
import com.app.web.repository.OrdenTrabajoRepositorio; // Cambio Vehiculo por OrdenTrabajo

@Service
public class OrdenTrabajoServicioImpl implements OrdenTrabajoServicio { // Cambio Vehiculo por OrdenTrabajo

    @Autowired
    private OrdenTrabajoRepositorio repositorio; // Cambio Vehiculo por OrdenTrabajo

    @Override
    public List<OrdenTrabajo> listarTodosLosOrdenesTrabajo() { // Cambio Vehiculo por OrdenTrabajo
        return repositorio.findAll();
    }

    @Override
    public OrdenTrabajo guardarOrdenTrabajo(OrdenTrabajo ordenTrabajo) { // Cambio Vehiculo por OrdenTrabajo
        return repositorio.save(ordenTrabajo);
    }

    @Override
    public OrdenTrabajo obtenerOrdenTrabajoPorID(Long id) { // Cambio Vehiculo por OrdenTrabajo
        return repositorio.findById(id).get();
    }

    @Override
    public OrdenTrabajo actualizarOrdenTrabajo(OrdenTrabajo ordenTrabajo) { // Cambio Vehiculo por OrdenTrabajo
        return repositorio.save(ordenTrabajo);
    }

    @Override
    public void eliminarOrdenTrabajo(Long id) { // Cambio Vehiculo por OrdenTrabajo
        repositorio.deleteById(id);
    }

}
