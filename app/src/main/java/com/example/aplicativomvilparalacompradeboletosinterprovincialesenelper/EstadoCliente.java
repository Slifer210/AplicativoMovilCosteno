package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;

public class EstadoCliente {
    private int idEstadoCliente;
    private String estado;

    // Constructor
    public EstadoCliente(int idEstadoCliente, String estado) {
        this.idEstadoCliente = idEstadoCliente;
        this.estado = estado;
    }

    // Getters and Setters
    public int getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(int idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
