package clases;

import java.util.List;

public interface Grafo
{

    public abstract Object obtener(int i);

    public abstract int orden();

    public abstract int costoArista(int i, int j);

    public abstract void insArista(int i, int j, int k);

    public abstract void elimArista(int i, int j);

    public abstract void insVertice(Object obj);

    public abstract List sucesores(int i);

    public abstract int buscar(Object obj);
}
