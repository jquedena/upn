package clases;

import java.awt.Point;

public class Barrio
{

    public Barrio(String nombre)
    {
        this.nombre = nombre;
        ubicacion = new Point(0, 0);
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Point getUbicacion()
    {
        return ubicacion;
    }

    public void setUbicacion(Point ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public int getNvisitas()
    {
        return nvisitas;
    }

    public void setNvisitas(int nvisitas)
    {
        this.nvisitas += nvisitas;
    }

    private String nombre;
    private Point ubicacion;
    private int nvisitas;
}
