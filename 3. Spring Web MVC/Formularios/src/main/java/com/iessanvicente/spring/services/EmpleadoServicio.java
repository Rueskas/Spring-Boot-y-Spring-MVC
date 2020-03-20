package com.iessanvicente.spring.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.iessanvicente.spring.models.Empleado;

@Service
public class EmpleadoServicio {

    private List<Empleado> repositorio = new ArrayList<>();


    public Empleado add(Empleado e) {
        repositorio.add(e);
        return e;
    }

    public List<Empleado> findAll() {
        return repositorio;
    }
    
    public Empleado findById(long id) {
    	return repositorio.stream()
    			.filter(p -> p.getId() == id)
    			.findFirst()
    			.orElse(null);
    }
    
    public boolean Update(Empleado e) {
    	Empleado empleado = repositorio.stream()
			.filter(p -> p.getId() == e.getId())
			.findFirst()
			.orElse(null);
    	if(empleado == null) {
    		return false;
    	} else {
    		int index = repositorio.indexOf(empleado);
    		repositorio.set(index, e);
    		return true;
    	}
    	
    }

    @PostConstruct
    public void init() {
        repositorio.addAll(
                Arrays.asList(new Empleado(1,"Antonio García", "antonio.garcia@openwebinars.net", "954000000"),
                        new Empleado(2,"María López", "maria.lopez@openwebinars.net", "954000000"),
                        new Empleado(3,"Ángel Antúnez", "angel.antunez@openwebinars.net", "954000000")                        
                        )
                );
    }
    
    

}