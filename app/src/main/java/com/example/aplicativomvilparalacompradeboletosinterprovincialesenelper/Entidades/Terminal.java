package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

public class Terminal {
    private int idTerminal;
    private String nombre;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String coordenadaLatitud;
    private String coordenadaLongitud;
    private String estado;

    // Constructor
    public Terminal(int idTerminal, String nombre, String direccion, String departamento, String provincia, String distrito, String coordenadaLatitud, String coordenadaLongitud, String estado) {
        this.idTerminal = idTerminal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.coordenadaLatitud = coordenadaLatitud;
        this.coordenadaLongitud = coordenadaLongitud;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(int idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCoordenadaLatitud() {
        return coordenadaLatitud;
    }

    public void setCoordenadaLatitud(String coordenadaLatitud) {
        this.coordenadaLatitud = coordenadaLatitud;
    }

    public String getCoordenadaLongitud() {
        return coordenadaLongitud;
    }

    public void setCoordenadaLongitud(String coordenadaLongitud) {
        this.coordenadaLongitud = coordenadaLongitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
