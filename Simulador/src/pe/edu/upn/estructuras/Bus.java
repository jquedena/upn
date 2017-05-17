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
public class Bus extends Vehiculo {
    
    private int nroPasajerosParadosSoportados;
    private int nroPasajerosParados;

    public int getNroPasajerosParadosSoportados() {
        return nroPasajerosParadosSoportados;
    }

    public void setNroPasajerosParadosSoportados(int nroPasajerosParadosSoportados) {
        this.nroPasajerosParadosSoportados = nroPasajerosParadosSoportados;
    }

    public int getNroPasajerosParados() {
        return nroPasajerosParados;
    }

    public void setNroPasajerosParados(int nroPasajerosParados) {
        this.nroPasajerosParados = nroPasajerosParados;
    }
    
}
