package com.app.web.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.OrdenTrabajo; // Cambio Vehiculo por OrdenTrabajo
import com.app.web.entidad.Servicio;

@Repository
public interface OrdenTrabajoRepositorio extends JpaRepository<OrdenTrabajo, Long> { // Cambio Vehiculo por OrdenTrabajo
	List<OrdenTrabajo> findByEliminadoFalse();
	List<OrdenTrabajo> findByEliminadoTrue();
	Set<OrdenTrabajo> findByServiciosContaining(Servicio servicio);
	@Query("SELECT ot FROM OrdenTrabajo ot " +
            "WHERE (:fecha IS NULL OR ot.fechaCreacion = :fecha)")
    List<OrdenTrabajo> filtrarOrdenesPorFecha(@Param("fecha") Date fecha);
	List<OrdenTrabajo> findByTecnicoIdAndFechaCreacionBetween(Long tecnicoId, Date fechaInicio, Date fechaFin);
	
}