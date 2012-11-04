package particionesdinamicas;

import modelo.Simulador;
import vistas.Principal;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ParticionesDinamicas {

    private static Simulador modelo;
    private static Principal ventanaPrincipal;

    /**
     * @return the modelo
     */
    public static Simulador getModelo() {
        return modelo;
    }

    /**
     * @param aModelo the modelo to set
     */
    public static void setModelo(Simulador aModelo) {
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
        ParticionesDinamicas.setModelo(new Simulador());
        setVentanaPrincipal(new Principal());
        getVentanaPrincipal().setVisible(true);
        getVentanaPrincipal().pack();
    }

}
