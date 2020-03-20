package com.iessanvicente.spring.repositorios;

import org.springframework.data.repository.CrudRepository;
import com.iessanvicente.spring.entidades.Entidad;

public interface Repositorio extends CrudRepository<Entidad, Long> {

}
