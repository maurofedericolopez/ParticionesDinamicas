package vistas;

import controladores.Controlador;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Mauro
 */
public class SliderChangeListener implements ChangeListener {

    private JSlider slider;
    private ParticionTableModel modeloTabla;
    private JLabel textoEvento;
    private Controlador controlador;

    public SliderChangeListener(EventosUI vista) {
        this.slider = vista.getSlider();
        this.modeloTabla = (ParticionTableModel) vista.getTablaEventos().getModel();
        this.textoEvento = vista.getTextoEvento();
        this.controlador = new Controlador();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Integer instante = slider.getValue();
        modeloTabla.setParticiones(controlador.obtenerPaticiones(instante));
        textoEvento.setText(controlador.obtenerEvento(instante));
    }

}
