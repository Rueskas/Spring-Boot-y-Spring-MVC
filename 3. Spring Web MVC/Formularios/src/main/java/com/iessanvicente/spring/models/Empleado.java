package com.iessanvicente.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Empleado {

	@Min(value=0, message="{empleado.id.mayorquecero}")
    private long id;
    @NotEmpty(message="{empleado.nombre}")
	private String nombre;
    @Email(message="{empleado.email}")
    private String email;
    private String telefono;
    private String imagen;
    
    public Empleado() {
    	
    }
    
	public Empleado(long id, String nombre, String email, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}
	
	
	public Empleado(@Min(value = 0, message = "{empleado.id.mayorquecero}") long id,
			@NotEmpty(message = "{empleado.nombre}") String nombre, @Email(message = "{empleado.email}") String email,
			String telefono, String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.imagen = imagen;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + "]";
	}


}