package com.iessanvicente.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iessanvicente.spring.models.Empleado;
import com.iessanvicente.spring.repositories.EmpleadoRepositorio;

@Primary
@Service("EmpleadoServiceDB")
public class EmpleadoServiceDB implements EmpleadoService {
	@Autowired
	private EmpleadoRepositorio repositorio;

	@Override
	public Empleado add(Empleado e) {
		return repositorio.save(e);
	}

	@Override
	public List<Empleado> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Empleado findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public boolean Update(Empleado e) {
		return repositorio.save(e) != null;
	}
	
	public List<Empleado> buscar(String cadena){
		return repositorio.findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(cadena, cadena, cadena);
	}
		
}
