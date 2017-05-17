package clases;

import java.util.*;

public class ColGrafo
    implements Grafo
{

    public ColGrafo()
    {
        grafo = new ArrayList();
        matcost = new int[20][20];
        matidentidad = new int[20][20];
    }

    public List getGrafo()
    {
        return grafo;
    }

    public void setGrafo(List grafo)
    {
        this.grafo = grafo;
    }

    public int[][] getMatcost()
    {
        return matcost;
    }

    public void setMatcost(int matcost[][])
    {
        this.matcost = matcost;
    }

    public int[][] getMatidentidad()
    {
        return matidentidad;
    }

    public void setMatidentidad(int matidentidad[][])
    {
        this.matidentidad = matidentidad;
    }

    public Object obtener(int i)
    {
        return grafo.get(i);
    }

    public int orden()
    {
        return grafo.size();
    }

    public int costoArista(int vi, int vf)
    {
        return matcost[vi][vf];
    }

    public void insArista(int vi, int vf, int costo)
    {
        matidentidad[vi][vf] = 1;
        matcost[vi][vf] = costo;
    }

    public void elimArista(int vi, int vf)
    {
        matcost[vi][vf] = 0x1869f;
        matidentidad[vi][vf] = 0;
    }

    public void insVertice(Object dato)
    {
        grafo.add(dato);
    }

    public List sucesores(int i)
    {
        List suc = new ArrayList();
        for(int j = 0; j < grafo.size(); j++)
            suc.add(Integer.valueOf(matidentidad[i][j]));

        return suc;
    }

    public int buscar(Object x)
    {
        for(Iterator iterator = grafo.iterator(); iterator.hasNext();)
        {
            Object b = (Object)iterator.next();
            if(b == x)
                return grafo.indexOf(b);
        }

        return 0;
    }

    List grafo;
    int matcost[][];
    int matidentidad[][];
}
