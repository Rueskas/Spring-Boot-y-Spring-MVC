package com.iessanvicente.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iessanvicente.spring.models.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
	List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String number);

	@Query("select e from Empleado e where lower(e.nombre) like %?1% or lower(e.email) like %?1% or lower(e.telefono) like %?1%")
	List<Empleado> findByContains(String cadena);
	
	@Query(value="SELECT E FROM EMPLEADO WHERE LOWER(NOMBRE) LIKE CONCAT('%', ?!, '%1') OR LOWER(EMAIL) LIKE CONCAT('%', ?!, '%1') OR LOWER(TELEFONO) LIKE CONCAT('%', ?!, '%1')", nativeQuery=true)
	List<Empleado> findByContainsNative(String cadena);
}
