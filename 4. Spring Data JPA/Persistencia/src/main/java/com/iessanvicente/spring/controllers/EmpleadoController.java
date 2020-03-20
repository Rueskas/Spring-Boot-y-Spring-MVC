package com.iessanvicente.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.iessanvicente.spring.models.Empleado;
import com.iessanvicente.spring.services.EmpleadoServiceDB;
import com.iessanvicente.spring.upload.storage.StorageService;



@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoServiceDB empleadoServicio;
	@Autowired StorageService storageService;
	
	@GetMapping({"/", "empleado/list"})
	public String listado(Model model, @RequestParam(name="q", required=false) String query) {
		if(query == null) {
			model.addAttribute("listaEmpleados", empleadoServicio.findAll());
		}
		else {
			model.addAttribute("listaEmpleados", empleadoServicio.buscar(query));
		}
		return "list";
	}
	
	@GetMapping("empleado/new")
	public String nuevo(Model model) {
		model.addAttribute("empleadoForm", new Empleado());
		return "form";
	}
	
	@PostMapping("empleado/new/submit")
	public String newSubmit(@ModelAttribute("empleadoForm") @Valid Empleado nuevoEmpleado,
			BindingResult bindingResult,
			@RequestParam MultipartFile file, 
			Model model) {

		if(bindingResult.hasErrors()) {
			return "form";
		} else {
			if(!file.isEmpty()) {
				String avatar = storageService.store(file, nuevoEmpleado.getId());
				System.out.println(avatar);
				nuevoEmpleado.setImagen(MvcUriComponentsBuilder
						.fromMethodName(EmpleadoController.class, "serveFile", avatar).build().toUriString());
			}
			empleadoServicio.add(nuevoEmpleado);
			return "redirect:/empleado/list";
		}
	}
	
	@GetMapping("empleado/update/{id}")
	public String editar(@PathVariable long id, Model model) {
		Empleado empleado = empleadoServicio.findById(id);
		if(empleado != null) {
			model.addAttribute("empleadoForm", empleado);
			return "form";
		}
		else {
			return "redirect:/empleado/list";
		}
	}
	
	@PostMapping("empleado/update/submit")
	public String updateSubmit(@Valid @ModelAttribute("empleadoForm") Empleado empleadoForm,
			BindingResult bindingResult,
			Model model) 
	{
		if(bindingResult.hasErrors()) {
			return "form";
		} else {
			boolean result = empleadoServicio.Update(empleadoForm);
			if(result == true) {
				return "redirect:/empleado/list";
			} else {
				return "redirect:/empleado/update/"+empleadoForm.getId();
			}
		}
	}
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
	}
}
