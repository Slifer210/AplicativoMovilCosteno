package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;
import java.util.Date;

public class Persona {
    private int idPersona;
    private int numDocumento;
    private String nombres;
    private String apellidos;
    private String estadoCivil;
    private String direccion;
    private String numTel;
    private Date fechaNac;
    private Date fechaCreacion;

    public Persona() {
    }

    public Persona(int idPersona, int numDocumento, String nombres, String apellidos, String estadoCivil, String direccion, String numTel, Date fechaNac, Date fechaCreacion) {
        this.idPersona = idPersona;
        this.numDocumento = numDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estadoCivil = estadoCivil;
        this.direccion = direccion;
        this.numTel = numTel;
        this.fechaNac = fechaNac;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(int numDocumento) {
        this.numDocumento = numDocumento;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
