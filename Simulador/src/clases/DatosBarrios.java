package clases;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DatosBarrios extends JFrame
{

    public DatosBarrios(final Barranquilla b, Estacion esta)
    {
        est = esta;
        c = getContentPane();
        c.setLayout(new FlowLayout());
        nombre = new JLabel("Nombre Del Barrio");
        txtnom = new JTextField(10);
        c.add(nombre);
        c.add(txtnom);
        guardar = new JButton("Guardar");
        guardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                b.getGBarrio().getColg().insVertice(new Barrio(txtnom.getText()));
                txtnom.setText("");
                JOptionPane.showMessageDialog(null, "A continuacion por favor seleccione la ubicacion y las rutas del barrio");
                setVisible(false);
                mf = new Imagenfondo();
                Visualizacion v = new Visualizacion(mf, b, est);
                v.setLocationRelativeTo(DatosBarrios.this);
                v.ubicar();
            }
        }
);
        c.add(guardar);
        setVisible(true);
        setSize(400, 100);
    }

    public DatosBarrios(final Barranquilla b, final int i)
    {
        c = getContentPane();
        c.setLayout(new FlowLayout());
        nombre = new JLabel("Nombre Del Barrio");
        txtnom = new JTextField(10);
        c.add(nombre);
        c.add(txtnom);
        guardar = new JButton("Guardar");
        guardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                ((Barrio)b.getGBarrio().getColg().obtener(i)).setNombre(txtnom.getText());
                txtnom.setText("");
                JOptionPane.showMessageDialog(null, "A continuacion por favor seleccione la ubicacion y las rutas del barrio");
                Aristas a = new Aristas(b);
                a.setLocationRelativeTo(DatosBarrios.this);
                setVisible(false);
            }
        }
);
        c.add(guardar);
        setTitle("Informacion Del Barrio");
        setVisible(true);
        setSize(400, 100);
    }

    private Container c;
    private JLabel nombre;
    private JTextField txtnom;
    private JButton guardar;
    private Imagenfondo mf;
    private Estacion est;




}
