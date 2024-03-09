package com.app.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.OrdenTrabajo;
import com.app.web.entidad.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
	List<Vehiculo> findByEliminadoFalse();
	List<Vehiculo> findByEliminadoTrue();
}
