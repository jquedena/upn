package clases;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.ImageIcon;

public class Moto
    implements Runnable
{

    public Moto()
    {
        ruta = null;
        indice = 0;
        placa = "PRO";
    }

    public void CrearMoto(Barranquilla ba, int x, int y, int inicio, int destino)
    {
        dinero = 10D;
        po = new Point(x, y);
        b = ba;
        indiceactual = inicio;
        indicefinal = destino;
        im = new ImageIcon(getClass().getResource("/Imagenes/moto.gif"));
    }

    public void run()
    {
        Point d;
        for(Iterator iterator = ruta.iterator(); iterator.hasNext(); move(d))
            d = (Point)iterator.next();

    }

    public Barranquilla getB()
    {
        return b;
    }

    public void setB(Barranquilla b)
    {
        this.b = b;
    }

    public ImageIcon getIm()
    {
        return im;
    }

    private void move(Point p)
    {
        double theta = Math.atan2(p.getY() - po.getY(), p.getX() - po.getX());
        Point q = new Point();
        q.setLocation(10D * Math.cos(theta), 10D * Math.sin(theta));
        while(po.distance(p) > 10D) 
        {
            po.setLocation(po.getX() + q.getX(), po.getY() + q.getY());
            theta = Math.atan2(p.getY() - po.getY(), p.getX() - po.getX());
            q.setLocation(10D * Math.cos(theta), 10D * Math.sin(theta));
            try
            {
                Thread.sleep(40L);
            }
            catch(Exception exception) { }
        }
        po.setLocation(p);
    }

    public void encomienda(LinkedList l, int destino)
    {
        ruta = l;
        indiceactual = destino;
        Thread t = new Thread(this);
        t.start();
    }

    public int getIndiceactual()
    {
        return indiceactual;
    }

    public double getDinero()
    {
        return dinero;
    }

    public void setDinero(double dinero)
    {
        this.dinero += dinero;
    }

    public Point getPo()
    {
        return po;
    }

    public void setPo(Point po)
    {
        this.po = po;
    }

    public LinkedList getRuta()
    {
        return ruta;
    }

    public void setRuta(LinkedList ruta)
    {
        this.ruta = ruta;
    }

    public int getIndice()
    {
        return indice;
    }

    public void setIndice(int Distancia)
    {
        indice = Distancia;
    }

    public int getIndicefinal()
    {
        return indicefinal;
    }

    public void setIndicefinal(int indicefinal)
    {
        this.indicefinal = indicefinal;
    }

    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    private double dinero;
    private Barranquilla b;
    private ImageIcon im;
    private int indiceactual;
    private int indicefinal;
    private LinkedList ruta;
    private Point po;
    private int indice;
    private String placa;
}
