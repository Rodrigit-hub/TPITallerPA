package com.app.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.web.entidad.Cliente;
import com.app.web.repository.ClienteRepositorio;

@DataJpaTest
public class ClienteTests {
	
	@Autowired
	private ClienteRepositorio repositorio;
	
	
	@Test
	public void testGuardarCliente() {
		Cliente cliente = new Cliente("Rodrigo","Alves", "rodri@gmail.com","4525566", "Santa fe 120", "DNI");
		repositorio.save(cliente);
	}
}
