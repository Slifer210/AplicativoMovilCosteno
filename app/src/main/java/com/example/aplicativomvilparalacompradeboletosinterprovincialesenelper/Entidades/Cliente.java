package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

import java.util.Date;

public class Cliente extends Persona {
    private String correo;
    private String password;
    private EstadoCliente estadoCliente;

    // Constructor
    public Cliente(int idPersona, int numDocumento, String nombres, String apellidos, String estadoCivil, String direccion, String numTel, Date fechaNac, Date fechaCreacion,
                   String correo, String password, EstadoCliente estadoCliente) {
        super(idPersona, numDocumento, nombres, apellidos, estadoCivil, direccion, numTel, fechaNac, fechaCreacion);
        this.correo = correo;
        this.password = password;
        this.estadoCliente = estadoCliente;
    }

    // Getters and Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EstadoCliente getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(EstadoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
    }
}