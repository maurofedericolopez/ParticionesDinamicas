package particionesdinamicas;

import modelo.Simulador;
import vistas.Principal;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ParticionesDinamicas {

    private static Simulador modelo;

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal ventanaPrincipal = new Principal();
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.pack();
    }

}
