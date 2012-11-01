package modelo;

import java.util.Observable;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Reloj extends Observable {

    private Integer instante;

    public Reloj() {
        super();
    }

    public void incrementarReloj() {
        this.instante++;
        this.setChanged();
        this.notifyObservers();
    }

    public void resetearReloj() {
        this.instante = 0;
    }

    public Integer obtenerInstante() {
        return this.instante;
    }

}
