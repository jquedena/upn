package clases;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel
{

    public PanelPrincipal()
    {
        img = new ImageIcon(getClass().getResource("../Imagenes/inicio1.jpg"));
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(img.getImage(), 0, 0, this);
    }

    ImageIcon img;
}
