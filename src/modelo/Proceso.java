 package modelo;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Proceso {

    private String nombre;
    private Integer tiempoArribo;
    private Integer tiempoCarga;
    private Integer tiempoPartida;
    private Integer duracionTrabajo;
    private Integer memoriaRequerida;

    public Proceso(String nombre, Integer tiempoArribo, Integer duracionTrabajo, Integer memoriaRequerida) {
        this.nombre = nombre;
        this.tiempoArribo = tiempoArribo;
        this.duracionTrabajo = duracionTrabajo;
        this.memoriaRequerida = memoriaRequerida;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tiempoArribo
     */
    public Integer getTiempoArribo() {
        return tiempoArribo;
    }

    /**
     * @param tiempoArribo the tiempoArribo to set
     */
    public void setTiempoArribo(Integer tiempoArribo) {
        this.tiempoArribo = tiempoArribo;
    }

    /**
     * @return the duracionTrabajo
     */
    public Integer getDuracionTrabajo() {
        return duracionTrabajo;
    }

    /**
     * @param duracionTrabajo the duracionTrabajo to set
     */
    public void setDuracionTrabajo(Integer duracionTrabajo) {
        this.duracionTrabajo = duracionTrabajo;
    }

    /**
     * @return the memoriaRequerida
     */
    public Integer getMemoriaRequerida() {
        return memoriaRequerida;
    }

    /**
     * @param memoriaRequerida the memoriaRequerida to set
     */
    public void setMemoriaRequerida(Integer memoriaRequerida) {
        this.memoriaRequerida = memoriaRequerida;
    }

    /**
     * @return the tiempoPartida
     */
    public Integer getTiempoPartida() {
        return tiempoPartida;
    }

    /**
     * @param tiempoPartida the tiempoPartida to set
     */
    public void setTiempoPartida(Integer tiempoPartida) {
        this.tiempoPartida = tiempoPartida;
    }

    /**
     * @return the tiempoCarga
     */
    public Integer getTiempoCarga() {
        return tiempoCarga;
    }

    /**
     * @param tiempoCarga the tiempoCarga to set
     */
    public void setTiempoCarga(Integer tiempoCarga) {
        this.tiempoCarga = tiempoCarga;
    }

}
