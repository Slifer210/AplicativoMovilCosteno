package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

import java.util.Date;

public class Chofer extends Empleado {


    private Date fechaLicencia;
    private String licenciaConducir;
    private boolean estado;

    public Chofer(Date fechaLicencia, String licenciaConducir, boolean estado) {
        this.fechaLicencia = fechaLicencia;
        this.licenciaConducir = licenciaConducir;
        this.estado = estado;
    }

    // Getters y Setters
    public Date getFechaLicencia() {
        return fechaLicencia;
    }

    public void setFechaLicencia(Date fechaLicencia) {
        this.fechaLicencia = fechaLicencia;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}