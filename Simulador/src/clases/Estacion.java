package clases;

import java.util.ArrayList;

public class Estacion
{

    public Estacion()
    {
        sw = false;
    }

    public void iniciarmoto()
    {
        sw = true;
    }

    public ArrayList getM()
    {
        return m;
    }

    public void setM(ArrayList m)
    {
        this.m = m;
    }

    public boolean isSw()
    {
        return sw;
    }

    public void setSw(boolean sw)
    {
        this.sw = sw;
    }

    private ArrayList m;
    private boolean sw;
}
