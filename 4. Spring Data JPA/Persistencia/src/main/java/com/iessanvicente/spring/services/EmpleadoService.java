package com.iessanvicente.spring.services;

import java.util.List;

import com.iessanvicente.spring.models.Empleado;

public interface EmpleadoService {
    public Empleado add(Empleado e);
    public List<Empleado> findAll();
    public Empleado findById(long id);
    public boolean Update(Empleado e);
}
