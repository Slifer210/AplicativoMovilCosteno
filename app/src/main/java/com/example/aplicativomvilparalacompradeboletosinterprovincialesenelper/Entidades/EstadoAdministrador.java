package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

public class EstadoAdministrador {
    private Long idEstadoAdministrador; // Asegúrate de tener un campo id para cada entidad
    private String estado;

    // Getters y Setters
    public Long getIdEstadoAdministrador() {
        return idEstadoAdministrador;
    }

    public void setIdEstadoAdministrador(Long idEstadoAdministrador) {
        this.idEstadoAdministrador = idEstadoAdministrador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
