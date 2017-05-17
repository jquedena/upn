package clases;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Principal extends JFrame implements ActionListener
{

    public Principal()
    {
        est = new Estacion();
        t = new Timer(5000, this);
        t.start();
        p = new PanelPrincipal();
        ba = new Barranquilla();
        mf = new Imagenfondo();
        barra = new JMenuBar();
        archivo = new JMenu("Archivo");
        insertar = new JMenu("Insertar");
        modificar = new JMenu("Modificar");
        ver = new JMenu("Ver");
        visualizar = new JMenuItem("Ver Mapa");
        visualizar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                v = new Visualizacion(mf, ba, est);
                v.setLocationRelativeTo(Principal.this);
            }
        }
);
        insBarrio = new JMenuItem("Insertar Barrio");
        insBarrio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                int n = ba.getGBarrio().getNv();
                db = new DatosBarrios(ba, est);
                db.setLocationRelativeTo(Principal.this);
            }
        }
);
        aristas = new JMenuItem("Insertar Rutas");
        aristas.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Aristas aristas = new Aristas(ba);
                aristas.setLocationRelativeTo(Principal.this);
            }
        }
);
        modificarbarrio = new JMenuItem("Modificar Barrio");
        modificarbarrio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                modificar m = new modificar(ba);
                m.setLocationRelativeTo(Principal.this);
            }
        }
);
        aislar = new JMenuItem("Aislar un Barrio");
        aislar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                AislarBarrio ais = new AislarBarrio(ba);
                ais.setLocationRelativeTo(Principal.this);
            }
        }
);
        restablecer = new JMenuItem("Restablecer");
        restablecer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                ba.ubicacionprede();
                ba.llenarMCostos(ba.getGBarrio().getColg().getMatcost());
                ba.llenarMIdentidad(ba.getGBarrio().getColg().getMatidentidad());
            }
        }
);
        reportes = new JMenuItem("Reportes");
        reportes.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                re = new Reporte(ba, est);
                re.setLocationRelativeTo(Principal.this);
            }
        }
);
        archivo.add(reportes);
        insertar.add(insBarrio);
        insertar.add(aristas);
        modificar.add(modificarbarrio);
        modificar.add(aislar);
        archivo.add(restablecer);
        ver.add(visualizar);
        barra.add(archivo);
        barra.add(insertar);
        barra.add(modificar);
        barra.add(ver);
        setJMenuBar(barra);
        ba.predeterminado();
        c=getContentPane();
        c.add(p);
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Estilo Visual no disponible en el sistema");
        }
    }
    
    public static void main(String arg[]){
    	Principal pr=new Principal();
    	pr.setVisible(true);
    	pr.setBounds(0, 0, 500, 400);
    	pr.setLocationRelativeTo(null);
    	pr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private Container c;
    private JMenuBar barra;
    private JMenu archivo;
    private JMenu ver;
    private JMenu insertar;
    private JMenu modificar;
    private JMenuItem visualizar;
    private JMenuItem aristas;
    private JMenuItem insBarrio;
    private JMenuItem modificarbarrio;
    private JMenuItem aislar;
    private JMenuItem restablecer;
    private JMenuItem reportes;
    private Visualizacion v;
    private Imagenfondo mf;
    private DatosBarrios db;
    private Barranquilla ba;
    private Timer t;
    private Estacion est;
    private PanelPrincipal p;
    private Reporte re;

}
