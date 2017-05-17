package clases;

public class GrafoBarrio
{

    public GrafoBarrio()
    {
        colg = new ColGrafo();
    }

    public int buscarBarrio(Grafo g, String nombre)
    {
        int z = -1;
        for(int i = 0; i < g.orden(); i++)
        {
            if(!((Barrio)g.obtener(i)).getNombre().equalsIgnoreCase(nombre))
                continue;
            z = i;
            break;
        }

        return z;
    }

    public int getNv()
    {
        return colg.orden();
    }

    public ColGrafo getColg()
    {
        return (ColGrafo)colg;
    }

    public void setColg(ColGrafo vertice)
    {
        colg = vertice;
    }

    private Grafo colg;
}
