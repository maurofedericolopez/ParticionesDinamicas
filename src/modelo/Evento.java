package modelo;

import java.util.LinkedList;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Evento {

    private LinkedList<Particion> particiones;
    private String cadenas;

    public Evento(LinkedList<Particion> particiones, String cadena) {
        this.particiones = particiones;
        this.cadenas = cadena;
    }

    /**
     * @return the particiones
     */
    public LinkedList<Particion> getParticiones() {
        return particiones;
    }

    /**
     * @param particiones the particiones to set
     */
    public void setParticiones(LinkedList<Particion> particiones) {
        this.particiones = particiones;
    }

    /**
     * @return the cadenas
     */
    public String getCadenas() {
        return cadenas;
    }

    /**
     * @param cadenas the cadenas to set
     */
    public void setCadenas(String cadenas) {
        this.cadenas = cadenas;
    }

}
