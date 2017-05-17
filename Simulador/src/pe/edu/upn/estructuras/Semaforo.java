/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.estructuras;

/**
 *
 * @author Estudiante
 */
public class Semaforo {
    
    private String nombre;
    private int tiempoVerde;
    private int tiempoAmbar;
    private int tiempoRojo;
    private double distancia;
    private Unidad unidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoVerde() {
        return tiempoVerde;
    }

    public void setTiempoVerde(int tiempoVerde) {
        this.tiempoVerde = tiempoVerde;
    }

    public int getTiempoAmbar() {
        return tiempoAmbar;
    }

    public void setTiempoAmbar(int tiempoAmbar) {
        this.tiempoAmbar = tiempoAmbar;
    }

    public int getTiempoRojo() {
        return tiempoRojo;
    }

    public void setTiempoRojo(int tiempoRojo) {
        this.tiempoRojo = tiempoRojo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
    
}
