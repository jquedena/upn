/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.estructuras.wrapper;

import pe.edu.upn.estructuras.Bus;
import pe.edu.upn.estructuras.Pasajero;
import pe.edu.upn.estructuras.utilitario.Utilitario;

/**
 *
 * @author JUAN
 */
public class PasajeroWrapper {
    
    private Pasajero pasajero;
    private VehiculoWrapper<Bus> vehiculoWrapper;

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public VehiculoWrapper getVehiculoWrapper() {
        return vehiculoWrapper;
    }

    public void setVehiculoWrapper(VehiculoWrapper vehiculoWrapper) {
        this.vehiculoWrapper = vehiculoWrapper;
    }
    
    public int ingresarEstacion() {
        int vi = pasajero.getNroInicial();
        int vf = pasajero.getNroInicial();
        return Utilitario.getInstancia().generaraAleatorio(vi, vf);
    } 
    
    public int subirSentadosAlBus() {
        int vf = vehiculoWrapper.getVehiculo().getNroPasajeros();
        return Utilitario.getInstancia().generaraAleatorio(0, vf); 
    }
    
    public int subirParadosAlBus() {
        int vf = vehiculoWrapper.getVehiculo().getNroPasajerosParadosSoportados();
        return Utilitario.getInstancia().generaraAleatorio(0, vf);
    }
    
    public int bajarSentadosDelBus() {
        int vf = vehiculoWrapper.getVehiculo().getNroAsientosOcupados();
        return Utilitario.getInstancia().generaraAleatorio(0, vf);
    }
    
    public int bajarParadosDelBus() {
        int vf = vehiculoWrapper.getVehiculo().getNroPasajerosParados();
        return Utilitario.getInstancia().generaraAleatorio(0, vf);
    }
}
