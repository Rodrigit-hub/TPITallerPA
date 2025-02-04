package com.app.web.controlador;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
        
     // Estadística de órdenes por mes (usando fechaCreacion)
        Map<String, Long> ordenesPorMes = new HashMap<>();

        for (OrdenTrabajo orden : todasLasOrdenes) {
            // Obtener la fecha de creación de la orden
            Date fechaCreacion = orden.getFechaCreacion();
            if (fechaCreacion != null) {
                // Convertir Date a LocalDate
                LocalDate fechaLocal = fechaCreacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String mesAno = fechaLocal.getMonth().toString() + " " + fechaLocal.getYear(); // Ej: "JANUARY 2025"
                
                // Agrupar las órdenes por mes
                ordenesPorMes.put(mesAno, ordenesPorMes.getOrDefault(mesAno, 0L) + 1);
            }
        }

        // Calcular el promedio de órdenes por mes
        double promedioPorMes = 0;
        if (!ordenesPorMes.isEmpty()) {
            promedioPorMes = ordenesPorMes.values().stream().mapToLong(Long::longValue).average().orElse(0);
        }

     // Estadística de órdenes por técnico y servicio
        Map<String, Map<String, Long>> ordenesPorTecnicoYServicio = new HashMap<>();

        for (OrdenTrabajo orden : todasLasOrdenes) {
            tecnico tecnicoAsignado = orden.getTecnico();
            if (tecnicoAsignado != null) {
                String nombreTecnico = tecnicoAsignado.getNombre() + " " + tecnicoAsignado.getApellido();
                
                // Inicializar mapa para el técnico si no existe
                ordenesPorTecnicoYServicio.putIfAbsent(nombreTecnico, new HashMap<>());
                
                for (Servicio servicio : orden.getServicios()) {
                    String nombreServicio = servicio.getNombre();
                    // Contabilizar el servicio realizado por el técnico
                    Map<String, Long> serviciosPorTecnico = ordenesPorTecnicoYServicio.get(nombreTecnico);
                    serviciosPorTecnico.put(nombreServicio, serviciosPorTecnico.getOrDefault(nombreServicio, 0L) + 1);
                }
            }
        }
     // Estadística de promedio de tiempo por orden (en días)
        List<Long> tiemposPorOrden = new ArrayList<>();
        long tiempoTotalEnDias = 0;
        int cantidadOrdenes = 0;

        for (OrdenTrabajo orden : todasLasOrdenes) {
            // Contabilizar los servicios
            for (Servicio servicio : orden.getServicios()) {
                recuentoServicios.put(servicio.getNombre(), recuentoServicios.getOrDefault(servicio.getNombre(), 0L) + 1);
            }

            // Contabilizar los técnicos
            tecnico tecnicoAsignado = orden.getTecnico();
            if (tecnicoAsignado != null) {
                String nombreCompleto = tecnicoAsignado.getNombre() + " " + tecnicoAsignado.getApellido();
                recuentoTecnicos.put(nombreCompleto, recuentoTecnicos.getOrDefault(nombreCompleto, 0L) + 1);
            }

            // Calcular el tiempo entre la fecha de creación y fecha de cierre en días
            if (orden.getFechaCreacion() != null && orden.getFechaCierre() != null) {
                long duracionEnMilisegundos = orden.getFechaCierre().getTime() - orden.getFechaCreacion().getTime();
                long duracionEnDias = duracionEnMilisegundos / (1000 * 60 * 60 * 24); // Convertir milisegundos a días
                tiemposPorOrden.add(duracionEnDias);
                tiempoTotalEnDias += duracionEnDias;
                cantidadOrdenes++;
            }
        }

        // Calcular el promedio de tiempo (en días)
        double promedioTiempoDias = 0;
        if (cantidadOrdenes > 0) {
            promedioTiempoDias = (double) tiempoTotalEnDias / cantidadOrdenes;
        }

        // Pasar el recuento de servicios y técnicos al modelo
        model.addAttribute("recuentoServicios", recuentoServicios);
        model.addAttribute("recuentoTecnicos", recuentoTecnicos);
        model.addAttribute("ordenesPorMes", ordenesPorMes);
        model.addAttribute("promedioPorMes", promedioPorMes);
        model.addAttribute("ordenesPorTecnicoYServicio", ordenesPorTecnicoYServicio);
        model.addAttribute("promedioTiempo", promedioTiempoDias);
        model.addAttribute("tiemposPorOrden", tiemposPorOrden); // Lista de tiempos por orden
       
        return "estadisticas";
    }
}
