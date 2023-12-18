package com.app.web.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entidad.Servicio; // Cambio el import y el uso de la clase
import com.app.web.repository.ServicioRepositorio; // Cambio el import y el uso de la clase

@Service
public class ServicioServicioImpl implements ServicioServicio { // Cambio el nombre de la clase e interfaz

    @Autowired
    private ServicioRepositorio repositorio; // Cambio el nombre del repositorio

    @Override
    public List<Servicio> listarTodosLosServicios() { // Cambio el nombre del método
        return repositorio.findAll();
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) { // Cambio el nombre del método
        return repositorio.save(servicio);
    }

    @Override
    public Servicio obtenerServicioPorId(Long id) { // Cambio el nombre del método
        return repositorio.findById(id).get();
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio) { // Cambio el nombre del método
        return repositorio.save(servicio);
    }

    @Override
    public void eliminarServicio(Long id) { // Cambio el nombre del método
        repositorio.deleteById(id);
    }
    @Override
    public Servicio agregarPrecio(Long id, BigDecimal precio) {
        Servicio servicio = obtenerServicioPorId(id);
        if (servicio != null) {
            servicio.setPrecio(precio);
            return repositorio.save(servicio);
        }
        return null; // O manejar de otra manera si el servicio no existe
    }


    @Override
    public Servicio actualizarPrecio(Long id, BigDecimal nuevoPrecio) {
        Servicio servicio = obtenerServicioPorId(id);
        if (servicio != null) {
            servicio.setPrecio(nuevoPrecio);
            return repositorio.save(servicio);
        }
        return null; // O manejar de otra manera si el servicio no existe
    }
}
