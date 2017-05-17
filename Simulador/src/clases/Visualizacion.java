package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Visualizacion extends JFrame
{

    public Visualizacion(Imagenfondo mf, Barranquilla ba, Estacion esta)
    {
        sw = false;
        m = new ArrayList();
        indicemoto = -1;
        nm = new ArrayList();
        swind = false;
        b = ba;
        ruta = new RutaMasCercana(b);
        est = esta;
        c = getContentPane();
        c.setLayout(new BorderLayout());
        nom = new String[b.getGBarrio().getNv()];
        p2 = new JPanel(new GridLayout(10, 1));
        p = new Panelvisualizacion(mf, b, est);
        p.start();
        barra = new JMenuBar();
        ver = new JMenu("Ver");
        gmaps = new JMenuItem("Ver en Google Maps");
        gmaps.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://roger1345.byethost17.com/mapa/mapa.html");
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al intentar abrir el mapa en internet");
                }
            }

        }
);
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e)
            {
                if(sw)
                {
                    Point p = new Point(e.getX() - 10, e.getY() - 90);
                    ((Barrio)b.getGBarrio().getColg().getGrafo().get(b.getGBarrio().getNv() - 1)).setUbicacion(p);
                    setVisible(false);
                    Aristas a = new Aristas(b);
                    a.setLocationRelativeTo(Visualizacion.this);
                }
            }

        }
);
        for(int i = 0; i < b.getGBarrio().getNv(); i++)
            nom[i] = ((Barrio)b.getGBarrio().getColg().getGrafo().get(i)).getNombre();

        barrios = new JComboBox(nom);
        barrios.setSelectedIndex(0);
        bllamar = new JButton("Llamar");
        bllamar.setBackground(Color.ORANGE);
        bllamar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    if(indicemoto < b.getM().size())
                    {
                        ruta.rutacorta();
                        ((Moto)m.get(indicemoto)).encomienda(ruta.ruta(((Moto)m.get(indicemoto)).getIndiceactual(), barrios.getSelectedIndex()), barrios.getSelectedIndex());
                        ((Moto)b.getM().get(indicemoto)).setDinero(500 * barrios.getSelectedIndex());
                        p.setSw(true);
                        p.setImgmoto(((Moto)m.get(indicemoto)).getIm());
                        p.setUbicacionmoto(((Moto)m.get(indicemoto)).getPo());
                        ((Barrio)b.getGBarrio().getColg().obtener(barrios.getSelectedIndex())).setNvisitas(1);
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Debe primero crear una moto");
                }
            }

        }
);
        bcrearmoto = new JButton("Crear moto");
        bcrearmoto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(swind)
                {
                    nm.remove(nm.size() - 1);
                    JOptionPane.showMessageDialog(null, "No se pueden crear mas motos despues de iniciar operaciones");
                } else
                {
                    indicemoto++;
                    nm.add((new StringBuilder()).append(indicemoto).toString());
                    seleccionarmoto.setListData(nm.toArray());
                    Moto r = new Moto();
                    m.add(r);
                    ((Moto)m.get(indicemoto)).setIndice(indicemoto);
                    ((Moto)m.get(indicemoto)).CrearMoto(b, ((Barrio)b.getGBarrio().getColg().obtener(0)).getUbicacion().x, ((Barrio)b.getGBarrio().getColg().obtener(0)).getUbicacion().y, 0, barrios.getSelectedIndex());
                    b.setM(m);
                    est.setM(m);
                }
            }

        }
);
        seleccionarmoto = new JList();
        seleccionarmoto.setSelectionMode(0);
        seleccionarmoto.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e)
            {
                swind = true;
                indicemoto = seleccionarmoto.getSelectedIndex();
            }

        }
);
        p2.add(barrios);
        p2.add(bllamar);
        p2.add(bcrearmoto);
        p2.add(new JScrollPane(seleccionarmoto));
        ver.add(gmaps);
        barra.add(ver);
        setJMenuBar(barra);
        c.add(p, "Center");
        c.add(p2, "East");
        setTitle("Mapa");
        setVisible(true);
        setSize(1000, 660);
    }

    public void ubicar()
    {
        sw = true;
    }

    private Container c;
    private Barranquilla b;
    private JMenuBar barra;
    private JMenu ver;
    private JMenuItem gmaps;
    private boolean sw;
    private Panelvisualizacion p;
    private JPanel p2;
    private JButton bllamar;
    private JComboBox barrios;
    private JList seleccionarmoto;
    private String nom[];
    private Estacion est;
    private ArrayList m;
    private RutaMasCercana ruta;
    private int indicemoto;
    private int indaux;
    private JButton bcrearmoto;
    ArrayList nm;
    private boolean swind;

}
