package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades;

import java.util.Date;

public class Viaje {

        private int idViaje;
        private Date fechaSalida;
        private Date fechaLlegada;
        private String horaSalida;
        private String horaLlegada;
        private Ruta ruta;
        private Administrador administrador;
        private Bus bus;
        private Chofer chofer1;
        private Chofer chofer2;

        // Constructor
        public Viaje(int idViaje, Date fechaSalida, Date fechaLlegada, String horaSalida, String horaLlegada, Ruta ruta, Administrador administrador, Bus bus, Chofer chofer1, Chofer chofer2) {
            this.idViaje = idViaje;
            this.fechaSalida = fechaSalida;
            this.fechaLlegada = fechaLlegada;
            this.horaSalida = horaSalida;
            this.horaLlegada = horaLlegada;
            this.ruta = ruta;
            this.administrador = administrador;
            this.bus = bus;
            this.chofer1 = chofer1;
            this.chofer2 = chofer2;
        }

        // Getters y Setters
        public int getIdViaje() {
            return idViaje;
        }

        public void setIdViaje(int idViaje) {
            this.idViaje = idViaje;
        }

        public Date getFechaSalida() {
            return fechaSalida;
        }

        public void setFechaSalida(Date fechaSalida) {
            this.fechaSalida = fechaSalida;
        }

        public Date getFechaLlegada() {
            return fechaLlegada;
        }

        public void setFechaLlegada(Date fechaLlegada) {
            this.fechaLlegada = fechaLlegada;
        }

        public String getHoraSalida() {
            return horaSalida;
        }

        public void setHoraSalida(String horaSalida) {
            this.horaSalida = horaSalida;
        }

        public String getHoraLlegada() {
            return horaLlegada;
        }

        public void setHoraLlegada(String horaLlegada) {
            this.horaLlegada = horaLlegada;
        }

        public Ruta getRuta() {
            return ruta;
        }

        public void setRuta(Ruta ruta) {
            this.ruta = ruta;
        }

        public Administrador getAdministrador() {
            return administrador;
        }

        public void setAdministrador(Administrador administrador) {
            this.administrador = administrador;
        }

        public Bus getBus() {
            return bus;
        }

        public void setBus(Bus bus) {
            this.bus = bus;
        }

        public Chofer getChofer1() {
            return chofer1;
        }

        public void setChofer1(Chofer chofer1) {
            this.chofer1 = chofer1;
        }

        public Chofer getChofer2() {
            return chofer2;
        }

        public void setChofer2(Chofer chofer2) {
            this.chofer2 = chofer2;
        }

}
