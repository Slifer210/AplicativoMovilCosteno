package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

public class Ruta {
    private int idRuta;
    private double distancia;
    private String duracion;
    private String estadoRuta;
    private Terminal terminalOrigen;
    private Terminal terminalDestino;
    private Administrador administrador;

    // Constructor
    public Ruta(int idRuta, double distancia, String duracion, String estadoRuta, Terminal terminalOrigen, Terminal terminalDestino, Administrador administrador) {
        this.idRuta = idRuta;
        this.distancia = distancia;
        this.duracion = duracion;
        this.estadoRuta = estadoRuta;
        this.terminalOrigen = terminalOrigen;
        this.terminalDestino = terminalDestino;
        this.administrador = administrador;
    }

    // Getters y Setters
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstadoRuta() {
        return estadoRuta;
    }

    public void setEstadoRuta(String estadoRuta) {
        this.estadoRuta = estadoRuta;
    }

    public Terminal getTerminalOrigen() {
        return terminalOrigen;
    }

    public void setTerminalOrigen(Terminal terminalOrigen) {
        this.terminalOrigen = terminalOrigen;
    }

    public Terminal getTerminalDestino() {
        return terminalDestino;
    }

    public void setTerminalDestino(Terminal terminalDestino) {
        this.terminalDestino = terminalDestino;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
