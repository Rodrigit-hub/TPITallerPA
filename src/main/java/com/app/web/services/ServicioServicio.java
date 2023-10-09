package com.app.web.services;

import java.util.List;
import com.app.web.entidad.Servicio; // Cambio el import y el uso de la clase

public interface ServicioServicio { // Cambio el nombre de la interfaz
    public List<Servicio> listarTodosLosServicios(); // Cambio el nombre del método

    public Servicio guardarServicio(Servicio servicio); // Cambio el nombre del método

    public Servicio obtenerServicioPorId(Long id); // Cambio el nombre del método

    public Servicio actualizarServicio(Servicio servicio); // Cambio el nombre del método

    public void eliminarServicio(Long id); // Cambio el nombre del método
}