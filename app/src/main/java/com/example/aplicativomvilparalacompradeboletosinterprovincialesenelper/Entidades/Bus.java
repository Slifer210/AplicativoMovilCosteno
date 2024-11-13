package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

public class Bus {
    private Long idBus;
    private String nombre;
    private String placa;
    private String modelo;
    private int capacidadPiso1;
    private int capacidadPiso2;


    private EstadoBus estadoBus;

    public Bus(Long idBus, String nombre, String placa, String modelo, int capacidadPiso1, int capacidadPiso2, EstadoBus estadoBus) {
        this.idBus = idBus;
        this.nombre = nombre;
        this.placa = placa;
        this.modelo = modelo;
        this.capacidadPiso1 = capacidadPiso1;
        this.capacidadPiso2 = capacidadPiso2;
        this.estadoBus = estadoBus;
    }

    // Getters y Setters
    public Long getIdBus() {
        return idBus;
    }

    public void setIdBus(Long idBus) {
        this.idBus = idBus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidadPiso1() {
        return capacidadPiso1;
    }

    public void setCapacidadPiso1(int capacidadPiso1) {
        this.capacidadPiso1 = capacidadPiso1;
    }

    public int getCapacidadPiso2() {
        return capacidadPiso2;
    }

    public void setCapacidadPiso2(int capacidadPiso2) {
        this.capacidadPiso2 = capacidadPiso2;
    }

    public EstadoBus getEstadoBus() {
        return estadoBus;
    }

    public void setEstadoBus(EstadoBus estadoBus) {
        this.estadoBus = estadoBus;
    }
}
