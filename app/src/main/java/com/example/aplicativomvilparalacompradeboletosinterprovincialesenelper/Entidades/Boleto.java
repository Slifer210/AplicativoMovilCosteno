package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

import java.nio.DoubleBuffer;
import java.sql.Time;
import java.util.Date;

public class Boleto {
    private Long boleto;
    private double precio;
    private Date fechaEmision;
    private Time horaEmision;
    private Cliente cliente;
    private Viaje viaje;
    private Bus bus;

    public Boleto() {
    }

    public Boleto(Long boleto, double precio, Date fechaEmision, Time horaEmision, Cliente cliente, Viaje viaje, Bus bus) {
        this.boleto = boleto;
        this.precio = precio;
        this.fechaEmision = fechaEmision;
        this.horaEmision = horaEmision;
        this.cliente = cliente;
        this.viaje = viaje;
        this.bus = bus;
    }

    public Long getBoleto() {
        return boleto;
    }

    public void setBoleto(Long boleto) {
        this.boleto = boleto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Time getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(Time horaEmision) {
        this.horaEmision = horaEmision;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
