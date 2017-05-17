package clases;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class modificar extends JFrame
{

    public modificar(Barranquilla ba)
    {
        b = ba;
        c = getContentPane();
        c.setLayout(new FlowLayout());
        l = new JLabel("Modificar Informacion del Barrio");
        l2 = new JLabel("Seleccione el Barrio");
        nom = new String[b.getGBarrio().getNv()];
        for(int i = 0; i < b.getGBarrio().getNv(); i++)
            nom[i] = ((Barrio)b.getGBarrio().getColg().getGrafo().get(i)).getNombre();

        barrios = new JComboBox(nom);
        barrios.setSelectedIndex(0);
        barrios.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                indice = barrios.getSelectedIndex();
            }
        }
);
        bm = new JButton("Modificar");
        bm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                DatosBarrios db = new DatosBarrios(b, indice);
                db.setLocationRelativeTo(modificar.this);
                setVisible(false);
            }
        }
);
        c.add(l);
        c.add(l2);
        c.add(barrios);
        c.add(bm);
        setTitle("Modificar Barrio");
        setVisible(true);
        setSize(300, 150);
    }

    private Container c;
    private Barranquilla b;
    private JLabel l;
    private JLabel l2;
    private JComboBox barrios;
    private String nom[];
    private JTextField t;
    private JButton bm;
    private int indice;

}
