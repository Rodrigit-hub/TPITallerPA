package com.app.web.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Servicio;
import com.app.web.entidad.Vehiculo;



@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long>{
	List<Servicio> findByEliminadoFalse();
	List<Servicio> findByEliminadoTrue();
    @Modifying
    @Query("UPDATE Servicio s SET s.precio = :precio WHERE s.id = :servicioId")
    void actualizarPrecio(@Param("servicioId") Long servicioId, @Param("precio") BigDecimal precio);
}

