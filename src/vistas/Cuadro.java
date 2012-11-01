package vistas;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Cuadro extends JComponent {

    public void pintar(Graphics g) {
        g.drawRect (5, 5, 5, 5);
    }

}
