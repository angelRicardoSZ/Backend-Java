package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="info")
public class Empleado {
	@Id  // simple primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // java generate automatic id
    @Column(name = "id_empleado")
    private Integer idEmpleado;
	
    @Column(name = "numero_empleado")
    private Integer numeroEmpleado;
    
    @Column(name = "primer_apellido")
    private String primerApellido;
    
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    
    
    private String ciudad;
    
    
    private String sexo;
    
    
    private String centro;
    
    private String puesto;
    
    
    @Column(name = "clave_interbancaria")
    private Integer claveInterbancaria;
    
    
    public Empleado() {
    	
    }

	public Integer getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}


	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}


	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}


	public String getSegundoApellido() {
		return segundoApellido;
	}


	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getCentro() {
		return centro;
	}


	public void setCentro(String centro) {
		this.centro = centro;
	}


	public String getPuesto() {
		return puesto;
	}


	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}


	public Integer getClaveInterbancaria() {
		return claveInterbancaria;
	}


	public void setClaveInterbancaria(Integer claveInterbancaria) {
		this.claveInterbancaria = claveInterbancaria;
	}
    
    
    
    
    
    
}
