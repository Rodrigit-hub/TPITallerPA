package com.app.web.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.app.web.entidad.OrdenTrabajo;
import com.app.web.entidad.Servicio;
import com.app.web.services.OrdenTrabajoServicio;

@Controller
public class EstadisticasControlador {
	
	@Autowired
	OrdenTrabajoServicio servicio;
	
	@GetMapping("/estadisticas")
	public String verEstadisticas(Model model) {
	    // Obtener todas las órdenes de trabajo
	    List<OrdenTrabajo> todasLasOrdenes = servicio.listarTodosLosOrdenesTrabajo();

	    // Inicializar un mapa para almacenar el recuento de cada servicio
	    Map<Servicio, Long> recuentoServicios = new HashMap<>();

	    // Iterar sobre las órdenes de trabajo y contar la frecuencia de cada servicio
	    for (OrdenTrabajo orden : todasLasOrdenes) {
	        for (Servicio servicio : orden.getServicios()) {
	            recuentoServicios.put(servicio, recuentoServicios.getOrDefault(servicio, 0L) + 1);
	        }
	    }

	    // Puedes agregar más lógica aquí para filtrar por año, si es necesario

	    // Pasar el recuento de servicios al modelo para que esté disponible en la vista
	    model.addAttribute("recuentoServicios", recuentoServicios);

	    return "estadisticas";
	}
}
