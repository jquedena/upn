package clases;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Reporte extends JFrame
{

    public Reporte(Barranquilla ba, Estacion est)
    {
        sw = true;
        this.est = est;
        ruta = new RutaMasCercana(ba);
        ruta.rutacorta();
        c = getContentPane();
        pp = new JTabbedPane();
        c.setLayout(new FlowLayout());
        p = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        String nom[] = new String[ba.getGBarrio().getNv()];
        Object info[][] = new Object[ba.getGBarrio().getNv()][ba.getGBarrio().getNv()];
        for(int fila = 0; fila < ba.getGBarrio().getNv(); fila++)
        {
            for(int col = 0; col < ba.getGBarrio().getNv(); col++)
                info[fila][col] = Integer.valueOf(ba.getGBarrio().getColg().getMatcost()[fila][col]);

        }

        for(int fila = 0; fila < ba.getGBarrio().getNv(); fila++)
        {
            for(int col = 0; col < ba.getGBarrio().getNv(); col++)
            {
                if(fila == col)
                    info[fila][col] = Integer.valueOf(0);
                if(info[fila][col].equals(Integer.valueOf(0x1869f)))
                    info[fila][col] = "inf";
            }

        }

        for(int col = 0; col < ba.getGBarrio().getNv(); col++)
            nom[col] = ((Barrio)ba.getGBarrio().getColg().getGrafo().get(col)).getNombre();

        modelo = new DefaultTableModel(info, nom);
        tablacostos = new JTable(modelo);
        String nomb[] = new String[ba.getGBarrio().getNv()];
        Object id[][] = new Object[ba.getGBarrio().getNv()][ba.getGBarrio().getNv()];
        for(int fila = 0; fila < ba.getGBarrio().getNv(); fila++)
        {
            for(int col = 0; col < ba.getGBarrio().getNv(); col++)
                id[fila][col] = Integer.valueOf(ba.getGBarrio().getColg().getMatidentidad()[fila][col]);

        }

        for(int fila = 0; fila < ba.getGBarrio().getNv(); fila++)
        {
            for(int col = 0; col < ba.getGBarrio().getNv(); col++)
                if(fila == col)
                    id[fila][col] = Integer.valueOf(0);

        }

        for(int col = 0; col < ba.getGBarrio().getNv(); col++)
            nomb[col] = ((Barrio)ba.getGBarrio().getColg().getGrafo().get(col)).getNombre();

        modeloid = new DefaultTableModel(id, nomb);
        tablaid = new JTable(modeloid);
        String columna[] = new String[ba.getGBarrio().getNv()];
        Object datos[][] = new Object[ba.getGBarrio().getNv()][ba.getGBarrio().getNv()];
        for(int fila = 0; fila < ba.getGBarrio().getNv(); fila++)
        {
            for(int col = 0; col < ba.getGBarrio().getNv(); col++)
                datos[fila][col] = Integer.valueOf(ruta.getR()[fila][col]);

        }

        for(int col = 0; col < ba.getGBarrio().getNv(); col++)
            columna[col] = ((Barrio)ba.getGBarrio().getColg().getGrafo().get(col)).getNombre();

        modelofloyd = new DefaultTableModel(datos, columna);
        tablafloyd = new JTable(modelofloyd);
        String coletiquetas[] = new String[3];
        Object datosreporte[][] = new Object[ba.getGBarrio().getNv()][3];
        coletiquetas[0] = "Nombre";
        coletiquetas[1] = "Ubicacion";
        coletiquetas[2] = "Numero de visitas";
        for(int fila = 0; fila < ba.getGBarrio().getNv(); fila++)
        {
            for(int col = 0; col < 3; col++)
                if(col == 0)
                    datosreporte[fila][col] = ((Barrio)ba.getGBarrio().getColg().obtener(fila)).getNombre();
                else
                if(col == 1)
                    datosreporte[fila][col] = (new StringBuilder(String.valueOf(((Barrio)ba.getGBarrio().getColg().obtener(fila)).getUbicacion().x))).append(" en X, ").append(((Barrio)ba.getGBarrio().getColg().obtener(fila)).getUbicacion().y).append(" en Y").toString();
                else
                    datosreporte[fila][col] = Integer.valueOf(((Barrio)ba.getGBarrio().getColg().obtener(fila)).getNvisitas());

        }

        tablacostos.setRowHeight(20);
        tablaid.setRowHeight(20);
        tablafloyd.setRowHeight(20);
        tablacostos.setEnabled(false);
        tablaid.setEnabled(false);
        tablafloyd.setEnabled(false);
        p2.add(new JScrollPane(tablaid));
        p.add(new JScrollPane(tablacostos));
        p3.add(new JScrollPane(tablafloyd));
        pp.addTab("Matriz de Costos", null, p, "Matriz de Costos");
        pp.addTab("Matriz de IDentidad", null, p2, "Matriz de Identidad");
        pp.addTab("Matriz de Floyd", null, p3, "Matriz de Floy");
        c.add(pp);
        if(sw)
        {
            setTitle("Reportes");
            setVisible(true);
            setSize(490, 390);
        }
    }

    private Container c;
    private JPanel p;
    private JPanel p2;
    private JPanel p3;
    private JTabbedPane pp;
    private JTable tablacostos;
    private JTable tablaid;
    private JTable tablafloyd;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloid;
    private DefaultTableModel modelofloyd;
    private RutaMasCercana ruta;
    private Estacion est;
    private boolean sw;
}
