package modelo;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Reloj {

    private Integer instante;

    public Reloj() {
        super();
        instante = 0;
    }

    public void incrementarReloj() {
        this.instante++;
    }

    public void resetearReloj() {
        this.instante = 0;
    }

    public Integer obtenerInstante() {
        return this.instante;
    }

    public void decrementarReloj() {
        this.instante--;
    }

}
