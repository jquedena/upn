package clases;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Aristas extends JFrame
{

    public Aristas(Barranquilla ba)
    {
        b = ba;
        c = getContentPane();
        c.setLayout(new BorderLayout());
        parriba = new JPanel(new FlowLayout(1));
        pcentro = new JPanel(new GridLayout(15, 4, 0, 4));
        pabajo = new JPanel(new FlowLayout(1));
        guardar = new JButton("Guardar");
        guardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                for(int i = 0; i < b.getGBarrio().getNv(); i++)
                    if(conecciones[i].isSelected())
                    {
                        if(costos[i].getText().equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Digite la distancia de todos los barrios a los que se conecta");
                            break;
                        }
                        b.getGBarrio().getColg().insArista(barrios.getSelectedIndex(), i, Integer.parseInt(costos[i].getText()));
                        setVisible(false);
                    } else
                    {
                        b.getGBarrio().getColg().elimArista(barrios.getSelectedIndex(), i);
                    }

            }
        }
);
        pabajo.add(guardar);
        nom = new String[b.getGBarrio().getNv()];
        for(int i = 0; i < b.getGBarrio().getNv(); i++)
            nom[i] = ((Barrio)b.getGBarrio().getColg().getGrafo().get(i)).getNombre();

        barrios = new JComboBox(nom);
        barrios.setSelectedIndex(0);
        parriba.add(barrios);
        conecciones = new JCheckBox[b.getGBarrio().getNv()];
        costos = new JTextField[b.getGBarrio().getNv()];
        for(int i = 0; i < b.getGBarrio().getNv(); i++)
        {
            nomrep = ((Barrio)b.getGBarrio().getColg().getGrafo().get(i)).getNombre();
            conecciones[i] = new JCheckBox(nomrep);
            conecciones[i].setVisible(false);
            pcentro.add(conecciones[i]);
            costos[i] = new JTextField(6);
            costos[i].setVisible(false);
            pcentro.add(costos[i]);
        }

        barrios.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                for(int i = 0; i < b.getGBarrio().getNv(); i++)
                {
                    nomrep = ((Barrio)b.getGBarrio().getColg().obtener(i)).getNombre();
                    if(!barrios.getSelectedItem().equals(nomrep))
                    {
                        conecciones[i].setVisible(true);
                        costos[i].setVisible(true);
                    } else
                    {
                        conecciones[i].setVisible(false);
                        costos[i].setVisible(false);
                    }
                    conecciones[i].setSelected(false);
                    costos[i].setText("");
                }

                establecerrutas(conecciones, costos);
            }
        }
);
        c.add(parriba, "North");
        c.add(pcentro, "Center");
        c.add(pabajo, "South");
        setTitle("Ver/Insertar Ruta");
        setVisible(true);
        setSize(300, 500);
    }

    public void establecerrutas(JCheckBox a[], JTextField c[])
    {
        for(int i = 0; i < b.getGBarrio().getNv(); i++)
            if(b.getGBarrio().getColg().getMatidentidad()[barrios.getSelectedIndex()][i] == 1)
            {
                conecciones[i].setSelected(true);
                costos[i].setText((new StringBuilder()).append(b.getGBarrio().getColg().costoArista(barrios.getSelectedIndex(), i)).toString());
            } else
            if(b.getGBarrio().getColg().getMatidentidad()[barrios.getSelectedIndex()][i] == 0)
            {
                conecciones[i].setSelected(false);
                costos[i].setText("");
            }

    }

    private Container c;
    private JComboBox barrios;
    private JCheckBox conecciones[];
    private JTextField costos[];
    private String nom[];
    private String nomrep;
    private JPanel parriba;
    private JPanel pcentro;
    private JPanel pabajo;
    private Barranquilla b;
    private JButton guardar;






}
