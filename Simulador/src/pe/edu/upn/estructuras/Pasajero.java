/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.estructuras;

import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
public class Pasajero implements Serializable {
    
    private int nroInicial;
    private int nroFinal;
    private int intervalo;

    public int getNroInicial() {
        return nroInicial;
    }

    public void setNroInicial(int nroInicial) {
        this.nroInicial = nroInicial;
    }

    public int getNroFinal() {
        return nroFinal;
    }

    public void setNroFinal(int nroFinal) {
        this.nroFinal = nroFinal;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
    
}
