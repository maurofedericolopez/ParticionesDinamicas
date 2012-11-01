package modelo;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Particion {

    private Integer direccionComienzo;
    private Integer tamaño;
    private EstadoParticion estado;
    private Proceso proceso;

    public Particion(Integer direccionComienzo, Integer tamaño) {
        this.direccionComienzo = direccionComienzo;
        this.tamaño = tamaño;
        this.estado = EstadoParticion.LIBRE;
    }

    /**
     * @return the direccionComienzo
     */
    public Integer getDireccionComienzo() {
        return direccionComienzo;
    }

    /**
     * @param direccionComienzo the direccionComienzo to set
     */
    public void setDireccionComienzo(Integer direccionComienzo) {
        this.direccionComienzo = direccionComienzo;
    }

    /**
     * @return the tamaño
     */
    public Integer getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(Integer tamaño) {
        this.tamaño = tamaño;
    }

    /**
     * @return the estado
     */
    public EstadoParticion getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoParticion estado) {
        this.estado = estado;
    }

    /**
     * @return the proceso
     */
    public Proceso getProceso() {
        return proceso;
    }

    /**
     * @param proceso the proceso to set
     */
    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

}
