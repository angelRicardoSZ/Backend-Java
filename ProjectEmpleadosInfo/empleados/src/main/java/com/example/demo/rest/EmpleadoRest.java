package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Empleado;
import com.example.demo.services.EmpleadoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("/{idEmpleado}")
	private ResponseEntity<Optional<Empleado>> geEmpleadotById(@PathVariable("idEmpleado") int idEmpleado) {
		System.out.println("Obtener Empleado por Id");
		return new ResponseEntity<>(empleadoService.findById(idEmpleado), HttpStatus.OK) ; 
	}
	
	/*@PostMapping
	private ResponseEntity<Empleado> savePersona (@RequestBody Empleado empleado){
		System.out.println("Nuevo empleado");
		System.out.println(empleado);
		
		try {
			Empleado empleadoGuardado = empleadoService.save(empleado);		
		return ResponseEntity.created(new URI("/empleados/"+empleadoGuardado.getIdEmpleado())).body(empleadoGuardado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}*/
	
	@PostMapping("/save")
	private ResponseEntity<Empleado> save(@RequestBody Empleado empleado){
		System.out.println("Nuevo empleado");
        return new ResponseEntity<>(empleadoService.save(empleado),HttpStatus.CREATED) ;
    }
}
