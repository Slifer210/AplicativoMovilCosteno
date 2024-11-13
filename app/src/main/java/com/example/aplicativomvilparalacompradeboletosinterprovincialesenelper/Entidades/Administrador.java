package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

import java.util.Date;

public class Administrador   extends Persona {
    private Long idAdministrador;  // Asegúrate de tener un campo id para cada entidad
    private String correo;
    private String password;


    private EstadoAdministrador estadoAdministrador;

    public Administrador(Long idAdministrador, String correo, String password, EstadoAdministrador estadoAdministrador) {
        this.idAdministrador = idAdministrador;
        this.correo = correo;
        this.password = password;
        this.estadoAdministrador = estadoAdministrador;
    }

    public Administrador(int idPersona, int numDocumento, String nombres, String apellidos, String estadoCivil, String direccion, String numTel, Date fechaNac, Date fechaCreacion, Long idAdministrador, String correo, String password, EstadoAdministrador estadoAdministrador) {
        super(idPersona, numDocumento, nombres, apellidos, estadoCivil, direccion, numTel, fechaNac, fechaCreacion);
        this.idAdministrador = idAdministrador;
        this.correo = correo;
        this.password = password;
        this.estadoAdministrador = estadoAdministrador;
    }

    // Getters y Setters
    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

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

    public EstadoAdministrador getEstadoAdministrador() {
        return estadoAdministrador;
    }

    public void setEstadoAdministrador(EstadoAdministrador estadoAdministrador) {
        this.estadoAdministrador = estadoAdministrador;
    }
}
