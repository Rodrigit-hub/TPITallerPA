package com.app.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entidad.OrdenTrabajo;
import com.app.web.entidad.Vehiculo;
import com.app.web.repository.VehiculoRepository;


@Service
public class VehiculoServiceImpl implements VehiculoService{
	
	@Autowired
	private VehiculoRepository repositorio;
	
	@Override
	public List<Vehiculo> listarTodosLosVehiculos() {
		return repositorio.findAll();
	}
    
	public List<Vehiculo> listarTodosLosVehiculosNoEliminados() {
        return repositorio.findByEliminadoFalse();
    }
	
	@Override
	public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
		return repositorio.save(vehiculo);
	}

	@Override
	public Vehiculo obtenerVehiculoPorID(Long id) {
		return repositorio.findById(id).get();
				
	}
	
	@Override
	public Vehiculo actualizarVehiculo(Vehiculo vehiculo) {
		return repositorio.save(vehiculo);
	}

	@Override
	public void eliminarVehiculo(Long id) {
		//repositorio.deleteById(id);
        Vehiculo vehiculo = repositorio.findById(id).orElse(null);
        if (vehiculo != null) {
        	vehiculo.setEliminado(true);
            repositorio.save(vehiculo);
        }
	}
    
	@Override
    public List<Vehiculo> listarTodosLosVehiculosEliminados() {
        return repositorio.findByEliminadoTrue();
    }
	
    public void restaurarVehiculo(Long id) {
	    Vehiculo vehiculo = repositorio.findById(id).orElse(null);
	    if (vehiculo != null) {
	    	vehiculo.setEliminado(false);
	        repositorio.save(vehiculo);
	    }
    }
}
