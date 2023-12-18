package com.app.web.controlador;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.web.entidad.Cliente;
import com.app.web.entidad.OrdenTrabajo; // Cambio Vehiculo por OrdenTrabajo
import com.app.web.entidad.Servicio;
import com.app.web.entidad.Vehiculo;
import com.app.web.services.ClienteServicioIMPL;
import com.app.web.services.OrdenTrabajoServicio; // Cambio Vehiculo por OrdenTrabajo
import com.app.web.services.ServicioServicioImpl;
import com.app.web.services.VehiculoServiceImpl;


@Controller
public class OrdenTrabajoControlador { // Cambio VehiculoControlador por OrdenTrabajoControlador

    @Autowired
    private OrdenTrabajoServicio servicio; // Cambio VehiculoService por OrdenTrabajoService

    /*
    @GetMapping("/ordentrabajo") // Cambio vehiculos por ordentrabajo
    public String listarOrdenesTrabajo(Model modelo) {
        modelo.addAttribute("ordentrabajo", servicio.listarTodosLosOrdenesTrabajo()); // Cambio vehiculos por ordentrabajo
        return "ordentrabajo"; // Nos retorna al archivo ordentrabajo
    }
    */
    
    @GetMapping("/ordentrabajo")
    public String listarOrdenes(Model modelo) {
        List<OrdenTrabajo> ordenes = servicio.listarTodasLasOrdenesNoEliminadas();
        modelo.addAttribute("ordentrabajo", ordenes);
        return "ordentrabajo";
    }
    
    @Autowired
    private VehiculoServiceImpl vehiculoService;
    @Autowired
    private ClienteServicioIMPL clienteService;
    @Autowired
    private ServicioServicioImpl servicioService;


    @GetMapping("/ordentrabajo/new") // Cambio vehiculos por ordentrabajo
    public String mostrarFormularioRegistroOrdenTrabajo(Model modelo) { // Cambio mostrarFormularioRegistroVehiculo por mostrarFormularioRegistroOrdenTrabajo
        OrdenTrabajo ordentrabajo = new OrdenTrabajo(); // Cambio Vehiculo por OrdenTrabajo
        Cliente cliente = new Cliente();
        Vehiculo vehiculo = new Vehiculo();

        List<Servicio> servicios = servicioService.listarTodosLosServicios();
        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        List<Vehiculo> vehiculos = vehiculoService.listarTodosLosVehiculos();

        modelo.addAttribute("ordentrabajo", ordentrabajo); // Cambio vehiculo por ordentrabajo
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("servicios", servicios);

        return "crear_ordentrabajo"; 
    }

    @PostMapping("/ordentrabajo")
    public String guardarOrdenTrabajo(@ModelAttribute("ordentrabajo") OrdenTrabajo ordentrabajo) { // Cambio vehiculo por ordentrabajo
        try {
            servicio.guardarOrdenTrabajo(ordentrabajo); 
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatePatenteException("La orden ya existe.");
        }
        return "redirect:/ordentrabajo"; 

    }

    @GetMapping("/ordentrabajo/editar/{id}") 
    public String mostrarFormEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("ordentrabajo", servicio.obtenerOrdenTrabajoPorID(id)); 
        OrdenTrabajo ordentrabajo = servicio.obtenerOrdenTrabajoPorID(id);
        List<Servicio> servicios = servicioService.listarTodosLosServicios();
        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        List<Vehiculo> vehiculos = vehiculoService.listarTodosLosVehiculos();

        modelo.addAttribute("ordentrabajo", ordentrabajo);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("servicios", servicios);

        return "editar_ordentrabajo"; 
    }

    @PostMapping("/ordentrabajo/{id}") 
    public String actualizarOrdenTrabajo(@PathVariable Long id,
            @ModelAttribute("ordentrabajo") OrdenTrabajo ordentrabajo, Model modelo) { 
        OrdenTrabajo ordentrabajoExistente = servicio.obtenerOrdenTrabajoPorID(id); 
        ordentrabajoExistente.setId(id);
        ordentrabajoExistente.setEstado(ordentrabajo.getEstado()); // Asegúrate de que el estado se establece correctamente

        servicio.actualizarOrdenTrabajo(ordentrabajoExistente); 
        return "redirect:/ordentrabajo"; 
    }

    @GetMapping("/ordentrabajo/{id}") 
    public String eliminarOrdenTrabajo(@PathVariable Long id) {
        servicio.eliminarOrdenTrabajo(id);
        return "redirect:/ordentrabajo"; 
    }
    
    //Cambio 2
    @GetMapping("/ordentrabajo/papelera")
    public String listarOrdenesEliminadas(Model modelo) {
        List<OrdenTrabajo> ordenesEliminadas = servicio.listarTodasLasOrdenesEliminadas();
        modelo.addAttribute("ordenesEliminadas", ordenesEliminadas);
        return "papelera";
    }
    
    @GetMapping("/ordentrabajo/restaurar/{id}")
    public String restaurarOrdenTrabajo(@PathVariable Long id) {
        servicio.restaurarOrdenTrabajo(id);
        return "redirect:/ordentrabajo";
    }
    
    @GetMapping("/ordentrabajo/filtrar")
    public String filtrarOrdenesTrabajo(
            @RequestParam(name = "fecha", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            Model modelo
    ) {
        List<OrdenTrabajo> ordenesFiltradas = servicio.filtrarOrdenesPorFecha(fecha);
        modelo.addAttribute("ordentrabajo", ordenesFiltradas);
        return "ordentrabajo";
    }

    

}
