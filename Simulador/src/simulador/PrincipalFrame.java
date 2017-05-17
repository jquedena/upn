
package simulador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PrincipalFrame extends JFrame implements ActionListener{
   
    private static final PrincipalFrame INSTANCIA = new PrincipalFrame();
    private JMenuBar mnuBar;
    private JMenu mnuArchivo;
    private JMenuItem mnuItem1;
    private JMenuItem mnuItem2;
    private JMenuItem mnuItem3;
    private JDesktopPane desktopPane;
    private DatosEntrada datosEntrada;
    private Paradas paradas;
    //private PuntoFormulario puntoFormulario;
   // private RutaFormulario rutaFormulario;
    
    private PrincipalFrame() {
        super();
        inicializar();
    }
   
    private void inicializar() {
       // puntoFormulario = new PuntoFormulario();
       // rutaFormulario = new RutaFormulario();
        datosEntrada = new DatosEntrada();
        paradas=new Paradas();
        
        mnuItem1 = new JMenuItem("Datos de Entrada");
        mnuItem1.addActionListener(this);
        
        mnuItem2 = new JMenuItem("Paradas");
        mnuItem2.addActionListener(this);
        
        mnuItem3 = new JMenuItem("Salir");
        mnuItem3.addActionListener(this);
        
        mnuArchivo = new JMenu("Archivo");
        mnuArchivo.add(mnuItem1);
        mnuArchivo.add(mnuItem2);
        mnuArchivo.addSeparator();
        mnuArchivo.add(mnuItem3);
        
        mnuBar = new JMenuBar();
        mnuBar.add(mnuArchivo);
        
        desktopPane = new JDesktopPane();
        //desktopPane.add(puntoFormulario);
        desktopPane.add(datosEntrada);
        desktopPane.add(paradas);
        add(desktopPane);
        
        setJMenuBar(mnuBar);
        setSize(720, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("EXAMEN FINAL");
    }
    
    public void mostrar() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(mnuItem1)) {
            datosEntrada.setVisible(true);
        } else if(e.getSource().equals(mnuItem2)) {
           //paradas.inicializarTabla();
           paradas.setVisible(true);
        } else if(e.getSource().equals(mnuItem3)) {
            System.exit(0);
        }
    }
    
    public static PrincipalFrame getInstancia() {
        return INSTANCIA;
    }
}
