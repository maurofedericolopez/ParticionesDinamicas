package particionesdinamicas;

import controladores.Controlador;
import modelo.GestorMemoria;
import vistas.Principal;
import vistas.SimulacionMemoriaUI;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ParticionesDinamicas {

    private static GestorMemoria modelo;
    private static Controlador controlador;
    private static Principal ventanaPrincipal;
    private static SimulacionMemoriaUI ventanaSimulacion;

    /**
     * @return the modelo
     */
    public static GestorMemoria getModelo() {
        return modelo;
    }

    /**
     * @param aModelo the modelo to set
     */
    public static void setModelo(GestorMemoria aModelo) {
        modelo = aModelo;
    }

    /**
     * @return the ventanaPrincipal
     */
    public static Principal getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    /**
     * @param aVentanaPrincipal the ventanaPrincipal to set
     */
    public static void setVentanaPrincipal(Principal aVentanaPrincipal) {
        ventanaPrincipal = aVentanaPrincipal;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ParticionesDinamicas.setModelo(new GestorMemoria());
        ParticionesDinamicas.setControlador(new Controlador());
        setVentanaPrincipal(new Principal());
        getVentanaPrincipal().setVisible(true);
        getVentanaPrincipal().pack();
        //new SimulacionMemoriaUI(null, true).setVisible(true);
    }

    /**
     * @return the ventanaSimulacion
     */
    public static SimulacionMemoriaUI getVentanaSimulacion() {
        return ventanaSimulacion;
    }

    /**
     * @param aVentanaSimulacion the ventanaSimulacion to set
     */
    public static void setVentanaSimulacion(SimulacionMemoriaUI aVentanaSimulacion) {
        ventanaSimulacion = aVentanaSimulacion;
        if(ventanaSimulacion != null) {
            ventanaSimulacion.setVisible(true);
            ventanaSimulacion.pack();
        }
    }

    /**
     * @return the controlador
     */
    public static Controlador getControlador() {
        return controlador;
    }

    /**
     * @param aControlador the controlador to set
     */
    public static void setControlador(Controlador aControlador) {
        controlador = aControlador;
    }

}
