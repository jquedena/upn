package clases;

import java.util.LinkedList;
import java.util.List;

public class RutaMasCercana
{

    public RutaMasCercana(Barranquilla ba)
    {
        b = ba;
        mf = new int[ba.getGBarrio().getNv()][ba.getGBarrio().getNv()];
        R = new int[b.getGBarrio().getNv()][b.getGBarrio().getNv()];
    }

    public void rutacorta()
    {
        for(int i = 0; i < b.getGBarrio().getNv(); i++)
        {
            for(int j = 0; j < b.getGBarrio().getNv(); j++)
            {
                R[i][j] = -1;
                mf[i][j] = b.getGBarrio().getColg().costoArista(i, j);
            }

        }

        for(int k = 0; k < R.length; k++)
        {
            for(int i = 0; i < R.length; i++)
            {
                for(int j = 0; j < R.length; j++)
                    if(mf[i][j] > mf[i][k] + mf[k][j])
                    {
                        mf[i][j] = mf[i][k] + mf[k][j];
                        R[i][j] = k;
                    }

            }

        }

    }

    public LinkedList ruta(int o, int d)
    {
        LinkedList camino = new LinkedList();
        if(R[o][d] != -1)
        {
            camino.addAll(ruta(o, R[o][d]));
            camino.add(((Barrio)b.getGBarrio().getColg().getGrafo().get(R[o][d])).getUbicacion());
            camino.addAll(ruta(R[o][d], d));
        }
        if(((Barrio)b.getGBarrio().getColg().getGrafo().get(o)).getUbicacion() == ((Barrio)b.getGBarrio().getColg().getGrafo().get(d)).getUbicacion())
            camino.removeAll(camino);
        camino.add(((Barrio)b.getGBarrio().getColg().getGrafo().get(d)).getUbicacion());
        return camino;
    }

    public int[][] getR()
    {
        return R;
    }

    public void setR(int R[][])
    {
        this.R = R;
    }

    private Barranquilla b;
    private int mf[][];
    private int R[][];
}
