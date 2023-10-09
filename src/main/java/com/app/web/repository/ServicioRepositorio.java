package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Servicio;



@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long>{

	
}
