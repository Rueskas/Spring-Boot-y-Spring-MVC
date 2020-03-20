package com.iessanvicente.spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.iessanvicente.spring.models.Empleado;
import com.iessanvicente.spring.repositories.EmpleadoRepositorio;
import com.iessanvicente.spring.upload.storage.StorageService;

@SpringBootApplication
public class FormulariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormulariosApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
	
	@Bean
	CommandLineRunner initData(EmpleadoRepositorio repositorio) {
		return (args) -> {
			/*
			Empleado empleado = new Empleado("Luis Miguel Garcia Lopez", "luismi.lopez@openwebinars.com", "945236657");
			Empleado empleado2 = new Empleado("Jose Garcia Lopez", "jose.lopez@openwebinars.com", "945236657");
			
			repositorio.save(empleado);
			repositorio.save(empleado2);
			
			repositorio.findAll().forEach(System.out::println);
			
			
			
			repositorio.saveAll(
					Arrays.asList(
							new Empleado("Luis Miguel Garcia Lopez", "luismi.lopez@openwebinars.com", "945236657"),
							new Empleado("Jose Garcia Lopez", "jose.lopez@openwebinars.com", "945236657")));
			 */
			
			List<String> nombres = Arrays.asList("Sergio", "Antonio", "Lucas", "Clara", "Marina", "Silvia", "Belen", "Mario");

			List<String> apellidos = Arrays.asList("Ruescas", "Arroyo", "Garcia", "Lopez", "Mena", "Crespo", "Llorca", "Perez");
			
			Collections.shuffle(nombres);
			
			repositorio.saveAll(
					IntStream.rangeClosed(1, nombres.size()).mapToObj((i) -> {
						String nombre = nombres.get(i - 1);
						String apellido1 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
						String apellido2 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
						return new Empleado(
								String.format("%s %s %s", nombre, apellido1, apellido2),
								String.format("%s.%s@openwebinars.com", nombre.toLowerCase(), apellido1.toLowerCase()),
								"934185543");
					}).collect(Collectors.toList())
					);
			
		
		};
	}
	
	

}
