package com.iessanvicente.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessanvicente.spring.models.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
	List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String number);
}
