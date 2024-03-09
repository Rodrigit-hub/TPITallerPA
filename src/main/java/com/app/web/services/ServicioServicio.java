package com.app.web.services;

import java.math.BigDecimal;
import java.util.List;

import com.app.web.entidad.Servicio; // Cambio el import y el uso de la clase
import com.app.web.entidad.Vehiculo;

public interface ServicioServicio { // Cambio el nombre de la interfaz
    public List<Servicio> listarTodosLosServicios(); // Cambio el nombre del método

    public Servicio guardarServicio(Servicio servicio); // Cambio el nombre del método

    public Servicio obtenerServicioPorId(Long id); // Cambio el nombre del método

    public Servicio actualizarServicio(Servicio servicio); // Cambio el nombre del método

    public void eliminarServicio(Long id); // Cambio el nombre del método
    
    public Servicio agregarPrecio(Long id, BigDecimal precio);

    public Servicio actualizarPrecio(Long id, BigDecimal nuevoPrecio);
    
    public List<Servicio> listarTodosLosServiciosNoEliminados();

    public List<Servicio> listarTodosLosServiciosEliminados();
    
    public void restaurarServicio(Long id);
    
}