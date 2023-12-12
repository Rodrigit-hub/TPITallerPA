package com.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.web.entidad.Cliente;
import com.app.web.entidad.Marca;
import com.app.web.entidad.Modelo;
import com.app.web.entidad.OrdenTrabajo;
import com.app.web.entidad.Servicio;
import com.app.web.entidad.Vehiculo;
import com.app.web.entidad.tecnico;
import com.app.web.repository.ClienteRepositorio;
import com.app.web.repository.MarcaRepository;
import com.app.web.repository.ModeloRepositorio;
import com.app.web.repository.OrdenTrabajoRepositorio;
import com.app.web.repository.ServicioRepositorio;
import com.app.web.repository.VehiculoRepository;
import com.app.web.repository.tecnicoRepositorio;

/**
 * Clase principal de la aplicación Spring Boot.
 * Implementa la interfaz CommandLineRunner para ejecutar tareas al iniciar la aplicación.
 */
@SpringBootApplication
public class CrudSpringBootApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootApplication.class, args); //Inicia la aplicación Spring Boot.
	}
	
	@Autowired
    private MarcaRepository repositorioMarca;
    
	@Autowired
	private VehiculoRepository repositorio;
	
	@Autowired 
	private tecnicoRepositorio repositorioTecnico;
	
	@Autowired 
	private ClienteRepositorio repositorioCliente;
	
	@Autowired
	private ModeloRepositorio repositorioModelo;

	@Autowired
	private ServicioRepositorio repositorioServicio;
	
	@Autowired
	private OrdenTrabajoRepositorio repositorioOrdenTrabajo;
	
	
	//este método es ejecutado al iniciar la aplicación.
    //carga de datos iniciales.
	@Override
	public void run(String... args) throws Exception {
	/*
		Servicio servicio1 = new Servicio("Cambio de aceite", "Cambia el aceite de un auto");
		repositorioServicio.save(servicio1);
		
		Servicio servicio2 = new Servicio("Mecanica ligera", "Cambiar amortiguador");
		repositorioServicio.save(servicio2);
		
		Vehiculo vehiculo1 = new Vehiculo("A033NFB");
		repositorio.save(vehiculo1);
		
		Vehiculo vehiculo2 = new Vehiculo("A332FDA");
		repositorio.save(vehiculo2);
		
        Marca marca1 = new Marca("FIAT");
        repositorioMarca.save(marca1);

        Marca marca2 = new Marca("PEUGEOT");
        repositorioMarca.save(marca2);
		
		tecnico tecnico1 = new tecnico("Pineda","Alvaro",3385688 );	
		repositorioTecnico.save(tecnico1);
	
		tecnico tecnico2 = new tecnico("Pepe","Salamaleco",3585688 );	
		repositorioTecnico.save(tecnico2);
		
		Cliente cliente = new Cliente("Celia","Mabel","celia69@example.com", 154250048, "Santa Fe 120", "DNI" );
		repositorioCliente.save(cliente);
		
		Cliente cliente1 = new Cliente("Juan", "Perez", "juan123@example.com", 123456789, "Calle A 123", "DNI");
		repositorioCliente.save(cliente1);

		Cliente cliente2 = new Cliente("Ana", "Gomez", "ana456@example.com", 987654321, "Calle B 456", "Cédula");
		repositorioCliente.save(cliente2);

		Cliente cliente3 = new Cliente("Celia", "Leila", "celia232@example.com", 154250048, "Santa Fe 120", "Pasaporte");
		repositorioCliente.save(cliente3);

		Cliente cliente4 = new Cliente("Luis", "Rodriguez", "luis777@example.com", 789012345, "Calle C 789", "DNI");
		repositorioCliente.save(cliente4);

		Cliente cliente5 = new Cliente("María", "Lopez", "maria555@example.com", 567890123, "Calle D 987", "Cédula");
		repositorioCliente.save(cliente5);
		
		repositorioCliente.save(cliente);
		Modelo modelo1 = new Modelo("Focus", "2001");
		repositorioModelo.save(modelo1);
		
		Modelo modelo2 = new Modelo("K", "2003");
		repositorioModelo.save(modelo2);
		
		Modelo modelo3 = new Modelo("Fiesta", "2009");
		repositorioModelo.save(modelo3);
*/
	}
}

