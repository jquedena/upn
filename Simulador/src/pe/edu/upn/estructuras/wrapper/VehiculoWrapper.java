/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.estructuras.wrapper;

import pe.edu.upn.estructuras.Vehiculo;

/**
 *
 * @author JUAN
 * @param <T>
 */
public class VehiculoWrapper<T extends Vehiculo> {
    
    private T vehiculo;

    public T getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(T vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    public void acelerar() {
        
    }

    public void desacelerar() {
        
    }
    
    public void frenar() {
        
    }
}
