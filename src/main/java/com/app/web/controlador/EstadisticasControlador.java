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
import com.app.web.entidad.tecnico;
import com.app.web.services.OrdenTrabajoServicio;
import com.app.web.services.tecnicoServicio;

@Controller
public class EstadisticasControlador {
    
    @Autowired
    OrdenTrabajoServicio ordenTrabajoServicio;
    
    @Autowired
    tecnicoServicio tecnicoServicio;
    
    @GetMapping("/estadisticas")
    public String verEstadisticas(Model model) {
        // Obtener todas las órdenes de trabajo
        List<OrdenTrabajo> todasLasOrdenes = ordenTrabajoServicio.listarTodosLosOrdenesTrabajo();

        // Estadística de servicios por orden (solo nombre del servicio)
        Map<String, Long> recuentoServicios = new HashMap<>();

        for (OrdenTrabajo orden : todasLasOrdenes) {
            for (Servicio servicio : orden.getServicios()) {
                recuentoServicios.put(servicio.getNombre(), recuentoServicios.getOrDefault(servicio.getNombre(), 0L) + 1);
            }
        }

        // Estadística de técnicos por orden (solo nombre y apellido)
        Map<String, Long> recuentoTecnicos = new HashMap<>();

        for (OrdenTrabajo orden : todasLasOrdenes) {
            tecnico tecnicoAsignado = orden.getTecnico(); // Asumiendo que hay un técnico asignado a cada orden
            if (tecnicoAsignado != null) {
                // Usamos nombre y apellido del técnico como clave
                String nombreCompleto = tecnicoAsignado.getNombre() + " " + tecnicoAsignado.getApellido();
                recuentoTecnicos.put(nombreCompleto, recuentoTecnicos.getOrDefault(nombreCompleto, 0L) + 1);
            }
        }

        // Pasar el recuento de servicios y técnicos al modelo
        model.addAttribute("recuentoServicios", recuentoServicios);
        model.addAttribute("recuentoTecnicos", recuentoTecnicos);

        return "estadisticas";
    }
}
