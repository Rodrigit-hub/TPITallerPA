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
    
    
    public List<OrdenTrabajo> listarTodasLasOrdenesNoEliminadas() {
        return repositorio.findByEliminadoFalse();
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
        //repositorio.deleteById(id); ya no se elimina
        OrdenTrabajo ordenTrabajo = repositorio.findById(id).orElse(null);
        if (ordenTrabajo != null) {
            ordenTrabajo.setEliminado(true);
            repositorio.save(ordenTrabajo);
        }
    }

    @Override
    public List<OrdenTrabajo> listarTodasLasOrdenesEliminadas() {
        return repositorio.findByEliminadoTrue();
    }

    public void restaurarOrdenTrabajo(Long id) {
        OrdenTrabajo ordenTrabajo = repositorio.findById(id).orElse(null);
        if (ordenTrabajo != null) {
            ordenTrabajo.setEliminado(false);
            repositorio.save(ordenTrabajo);
        }
    }
}
