package clases;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Barranquilla
{

    public Barranquilla()
    {
        nm = 1;
        gbarrio = new GrafoBarrio();
        aristas = new int[20][20];
        costos = new int[20][20];
        inf = 0x1869f;
        m = new ArrayList();
    }

    public GrafoBarrio getGBarrio()
    {
        return gbarrio;
    }

    public void setGBarrio(GrafoBarrio g)
    {
        gbarrio = g;
    }

    public ArrayList getM()
    {
        return m;
    }

    public void setM(ArrayList m)
    {
        this.m = m;
    }

    public void predeterminado()
    {
        gbarrio.getColg().insVertice(new Barrio("Las moras"));
        gbarrio.getColg().insVertice(new Barrio("Rebolo"));
        gbarrio.getColg().insVertice(new Barrio("Montecristo"));
        gbarrio.getColg().insVertice(new Barrio("Prado"));
        gbarrio.getColg().insVertice(new Barrio("Ciudadela"));
        gbarrio.getColg().insVertice(new Barrio("Villa santos"));
        gbarrio.getColg().insVertice(new Barrio("Tres Postes"));
        gbarrio.getColg().insVertice(new Barrio("Modelo"));
        gbarrio.getColg().insVertice(new Barrio("Barrio Abajo"));
        gbarrio.getColg().insVertice(new Barrio("Boston"));
        for(int i = 0; i < aristas.length; i++)
        {
            for(int j = 0; j < aristas[i].length; j++)
            {
                costos[i][j] = inf;
                aristas[i][j] = 0;
            }

        }

        llenarMIdentidad(aristas);
        llenarMCostos(costos);
        gbarrio.getColg().setMatidentidad(aristas);
        gbarrio.getColg().setMatcost(costos);
        ubicacionprede();
    }

    public void ubicacionprede()
    {
        ((Barrio)gbarrio.getColg().getGrafo().get(0)).setUbicacion(new java.awt.Point(100, 190));
        ((Barrio)gbarrio.getColg().getGrafo().get(1)).setUbicacion(new java.awt.Point(250, 130));
        ((Barrio)gbarrio.getColg().getGrafo().get(2)).setUbicacion(new java.awt.Point(380, 180));
        ((Barrio)gbarrio.getColg().getGrafo().get(3)).setUbicacion(new java.awt.Point(500, 100));
        ((Barrio)gbarrio.getColg().getGrafo().get(4)).setUbicacion(new java.awt.Point(150, 490));
        ((Barrio)gbarrio.getColg().getGrafo().get(5)).setUbicacion(new java.awt.Point(330, 430));
        ((Barrio)gbarrio.getColg().getGrafo().get(6)).setUbicacion(new java.awt.Point(450, 490));
        ((Barrio)gbarrio.getColg().getGrafo().get(7)).setUbicacion(new java.awt.Point(380, 320));
        ((Barrio)gbarrio.getColg().getGrafo().get(8)).setUbicacion(new java.awt.Point(600, 460));
        ((Barrio)gbarrio.getColg().getGrafo().get(9)).setUbicacion(new java.awt.Point(650, 320));
    }

    public void llenarMIdentidad(int m[][])
    {
        m[0][1] = 1;
        m[0][2] = 1;
        m[1][2] = 1;
        m[1][4] = 1;
        m[2][3] = 1;
        m[2][8] = 1;
        m[3][4] = 1;
        m[4][5] = 1;
        m[4][7] = 1;
        m[5][6] = 1;
        m[6][7] = 1;
        m[7][8] = 1;
        m[8][0] = 1;
        m[8][9] = 1;
        m[9][0] = 1;
    }

    public void llenarMCostos(int m[][])
    {
        m[0][1] = 2;
        m[0][2] = 4;
        m[1][2] = 3;
        m[1][4] = 12;
        m[2][3] = 1;
        m[2][8] = 11;
        m[3][4] = 14;
        m[4][5] = 6;
        m[4][7] = 13;
        m[5][6] = 7;
        m[6][7] = 5;
        m[7][8] = 9;
        m[8][0] = 15;
        m[8][9] = 8;
        m[9][0] = 10;
    }

    public void guardar(RandomAccessFile a)
        throws IOException
    {
        for(int i = 10; i < gbarrio.getNv(); i++)
        {
            a.writeUTF(((Barrio)gbarrio.getColg().getGrafo().get(i)).getNombre());
            a.writeUTF(";");
        }

    }

    public void cargar(RandomAccessFile a)
        throws IOException
    {
        gbarrio.getColg().insVertice(new Barrio(a.readUTF()));
    }

    public int getNm()
    {
        return nm;
    }

    public void setNm(int nm)
    {
        this.nm = nm;
    }

    private GrafoBarrio gbarrio;
    private ArrayList m;
    private int aristas[][];
    private int costos[][];
    private int inf;
    private int nm;
}
