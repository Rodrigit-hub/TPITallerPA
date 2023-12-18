package com.app.web.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Servicio;



@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long>{

    @Modifying
    @Query("UPDATE Servicio s SET s.precio = :precio WHERE s.id = :servicioId")
    void actualizarPrecio(@Param("servicioId") Long servicioId, @Param("precio") BigDecimal precio);
}

