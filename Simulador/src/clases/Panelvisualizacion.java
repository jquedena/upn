package clases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Panelvisualizacion extends JPanel
    implements Runnable
{

    public Panelvisualizacion(Imagenfondo mf, Barranquilla ba, Estacion est)
    {
        sw = false;
        b = ba;
        this.est = est;
        flagest = new ImageIcon(getClass().getResource("/Imagenes/banderaestacion.gif"));
        if(mf.getIm() == null)
            im = new ImageIcon(getClass().getResource("/Imagenes/mapa barranquilla1.jpg"));
        else
            im = mf.getIm();
        flag = new ImageIcon(getClass().getResource("/Imagenes/bandera.gif"));
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(im.getImage(), 0, 0, 800, 600, this);
        int x = 0;
        int y = 0;
        g2.setFont(new Font("arial black", 1, 16));
        g2.drawImage(flagest.getImage(), (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(0)).getUbicacion().getX(), (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(0)).getUbicacion().getY(), 30, 30, this);
        g2.drawString("0", (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(0)).getUbicacion().getX(), (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(0)).getUbicacion().getY() - 10);
        for(int i = 1; i < b.getGBarrio().getNv(); i++)
        {
            x = (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(i)).getUbicacion().getX();
            y = (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(i)).getUbicacion().getY();
            g2.drawImage(flag.getImage(), x, y, 30, 30, this);
            g2.drawString((new StringBuilder()).append(i).toString(), x - 10, y - 10);
        }

        g2.setStroke(new BasicStroke(3F));
        for(int j = 0; j < b.getGBarrio().getNv(); j++)
        {
            for(int k = 0; k < b.getGBarrio().getNv(); k++)
                if(b.getGBarrio().getColg().getMatidentidad()[j][k] == 1)
                {
                    int x1 = (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(j)).getUbicacion().getX() + 5;
                    int x2 = (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(k)).getUbicacion().getX() + 5;
                    int y1 = (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(j)).getUbicacion().getY() + 35;
                    int y2 = (int)((Barrio)b.getGBarrio().getColg().getGrafo().get(k)).getUbicacion().getY() + 35;
                    g.drawLine(x1, y1, x2, y2);
                    g.setColor(Color.RED);
                    g.drawString((new StringBuilder()).append(b.getGBarrio().getColg().getMatcost()[j][k]).toString(), (x1 + x2) / 2, (y1 + y2) / 2);
                    g.setColor(Color.BLACK);
                }

        }

        if(sw)
        {
            g2.setColor(Color.BLUE);
            Moto aux;
            for(Iterator iterator = est.getM().iterator(); iterator.hasNext(); g2.drawString((new StringBuilder()).append(aux.getIndice()).toString(), aux.getPo().x, aux.getPo().y - 15))
            {
                aux = (Moto)iterator.next();
                g2.drawImage(imgmoto.getImage(), aux.getPo().x, aux.getPo().y, 30, 30, this);
            }

        }
    }

    public void run()
    {
        do
        {
            repaint();
            try
            {
                Thread.sleep(50L);
            }
            catch(InterruptedException ex)
            {
               
            }
        } while(true);
    }

    public void start()
    {
        Thread t = new Thread(this);
        t.start();
    }

    public ImageIcon getImgmoto()
    {
        return imgmoto;
    }

    public void setImgmoto(ImageIcon imgmoto)
    {
        this.imgmoto = imgmoto;
    }

    public Point getUbicacionmoto()
    {
        return ubicacionmoto;
    }

    public void setUbicacionmoto(Point ubicacionmoto)
    {
        this.ubicacionmoto = ubicacionmoto;
    }

    public boolean isSw()
    {
        return sw;
    }

    public void setSw(boolean sw)
    {
        this.sw = sw;
    }

    private ImageIcon im;
    private ImageIcon flagest;
    private Barranquilla b;
    private ImageIcon flag;
    private ImageIcon imgmoto;
    private Estacion est;
    private Point ubicacionmoto;
    private boolean sw;
}
