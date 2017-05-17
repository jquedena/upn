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

public class AislarBarrio extends JFrame
{

    public AislarBarrio(Barranquilla ba)
    {
        c = getContentPane();
        c.setLayout(new FlowLayout());
        b = ba;
        nom = new String[b.getGBarrio().getNv()];
        for(int i = 0; i < b.getGBarrio().getNv(); i++)
            nom[i] = ((Barrio)b.getGBarrio().getColg().getGrafo().get(i)).getNombre();

        barrios = new JComboBox(nom);
        barrios.setSelectedIndex(0);
        barrios.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)
            {
                indicebarrio = barrios.getSelectedIndex();
            }

        }
);
        baislar = new JButton("Aislar");
        baislar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                for(int i = 0; i < b.getGBarrio().getNv(); i++)
                {
                    b.getGBarrio().getColg().elimArista(indicebarrio, i);
                    b.getGBarrio().getColg().elimArista(i, indicebarrio);
                }

                setVisible(false);
            }
        }
);
        c.add(barrios);
        c.add(baislar);
        setVisible(true);
        setSize(200, 100);
    }

    private Container c;
    private JComboBox barrios;
    private String nom[];
    private Barranquilla b;
    private JButton baislar;
    private int indicebarrio;




}
