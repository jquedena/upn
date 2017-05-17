/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.estructuras.utilitario;

import java.util.Random;

/**
 *
 * @author JUAN
 */
public class Utilitario {

    private static final Utilitario INSTANCIA = new Utilitario();    
    private final Random random = new Random();
   
    private Utilitario(){
    }

    public int generaraAleatorio(int vi, int vf) {
        return random.nextInt(vf - vi) + vi;
    }
    
    public static Utilitario getInstancia() {
        return INSTANCIA;
    }
}
