package com.app.web.services;

import java.util.Date;
import java.util.List;

import com.app.web.entidad.OrdenTrabajo; // Cambio Vehiculo por OrdenTrabajo
import com.app.web.entidad.Servicio;

public interface OrdenTrabajoServicio {

    public List<OrdenTrabajo> listarTodosLosOrdenesTrabajo(); // Cambio Vehiculo por OrdenTrabajo

    public OrdenTrabajo guardarOrdenTrabajo(OrdenTrabajo ordenTrabajo); // Cambio Vehiculo por OrdenTrabajo

    public OrdenTrabajo obtenerOrdenTrabajoPorID(Long id); // Cambio Vehiculo por OrdenTrabajo

    public OrdenTrabajo actualizarOrdenTrabajo(OrdenTrabajo ordenTrabajo); // Cambio Vehiculo por OrdenTrabajo

    public void eliminarOrdenTrabajo(Long id); // Cambio Vehiculo por OrdenTrabajo
    
    public List<OrdenTrabajo> listarTodasLasOrdenesNoEliminadas();

    public List<OrdenTrabajo> listarTodasLasOrdenesEliminadas();
    
    public void restaurarOrdenTrabajo(Long id);

	void eliminarServicio(OrdenTrabajo ordenTrabajo, Servicio servicio);

	void agregarServicio(OrdenTrabajo ordenTrabajo, Servicio servicio);

	List<OrdenTrabajo> filtrarOrdenesPorFecha(Date fecha);
    
}