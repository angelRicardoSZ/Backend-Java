package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Empleado;
import com.example.demo.services.EmpleadoService;

import java.util.List;

@RestController
@RequestMapping("/empleados/")
public class EmpleadoRest {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/all")
	private ResponseEntity<List<Empleado>> findAll() {
		System.out.println("Obtener todos los empleados");
		return new ResponseEntity<>(empleadoService.findAll(), HttpStatus.OK) ;
	}
	
}
