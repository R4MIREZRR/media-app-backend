package com.mitocode.model;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

// TODO: 29/01/24 Schema -> es para editar documentacion en el swagger  
@Schema(description = "Paciente model")
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    // TODO: 29/01/24  @SIZE -> desde 2.3 SPRING BOOT debemos agregar manualmente  SPRING-BOOT-STARTER-VALIDATION 
    // TODO: 29/01/24 Para creacion de las tablas se pone el maximo y cuando ejecutes no te dejara poner mas q eso pero el minimo no estsa definido, por eso se agrega el @Size 
    @Size(min = 3, message = "{nombres.size}")
    @Column(name = "nombres", nullable = false, length = 70)
    private String nombres;

    // TODO: 29/01/24 {apellidos.size} -> debe hacer match con la llave del properties messages, es una expresion lenguage pero propia de estos 
    @Size(min = 3, message = "{apellidos.size}")
    @Column(name = "apellidos", nullable = false, length = 70)
     private String apellidos;
    @Size(min = 8, max = 8, message = " DNI del paciente debe tener 8 caracteres")
    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;
    @Size(min = 3, max = 150, message = " direccion del paciente debe tener minimo 3 caracteres")
    @Column(name = "direccion", nullable = true, length = 150)
    private String direccion;

    @Size(min = 3, max = 9, message = " Telefono del paciente debe tener 9 caracteres")
    @Column(name = "telefono", nullable = true, length = 9)
    private String telefono;

    // TODO: 29/01/24 @Email -> ya te valida todo lo q debe tener un email de lo contrario deberisas agregar un pattern con regexp para q valides 
    @Email(message = "Email formato incorrecto.")
    @Column(name = "email", nullable = true, length = 55)
    private String email;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
