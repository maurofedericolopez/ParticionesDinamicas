package controladores;

import java.util.Observable;
import modelo.*;
import particionesdinamicas.ParticionesDinamicas;
import vistas.Principal;
import vistas.ProcesoUI;
import vistas.ResultadoUI;
import vistas.SimuladorUI;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Controlador extends Observable {

    private Simulador modelo;
    private Evento evento;

    public Controlador() {
        super();
        this.modelo = ParticionesDinamicas.getModelo();
    }

    public void cargarDatosSimulador(String tamañoMemoriaFisica, String tiempoSeleccionParticion, String tiempoCargaPromedio, String tiempoLiberacionParticion, Estrategia estrategiaSeleccionParticion)
                                     throws NumberFormatException, NullPointerException {
        Integer TMF = Integer.parseInt(tamañoMemoriaFisica);
        Integer TSP = Integer.parseInt(tiempoSeleccionParticion);
        Integer TCP = Integer.parseInt(tiempoCargaPromedio);
        Integer TLP = Integer.parseInt(tiempoLiberacionParticion);
        this.modelo.setTamañoMemoriaFisica(TMF);
        this.modelo.setTiempoSeleccionParticion(TSP);
        this.modelo.setTiempoCargaPromedio(TCP);
        this.modelo.setTiempoLiberacionParticion(TLP);
        this.modelo.setEstrategiaSeleccionParticion(estrategiaSeleccionParticion);
    }

    public void eliminarProceso(int rowIndex) {
        if(rowIndex >= 0)
            this.modelo.getProcesosEsperando().remove(rowIndex);
        setChanged();
        notifyObservers();
    }

    public Particion getParticion(int rowIndex) {
        return this.modelo.getEstrategiaSeleccionParticion().getParticiones().get(rowIndex);
    }

    public int getParticionCount() {
        return this.modelo.getEstrategiaSeleccionParticion().getParticiones().size();
    }

    public Proceso getProcesoEsperando(int rowIndex) {
        return this.modelo.getProcesosEsperando().get(rowIndex);
    }

    public int getProcesoEsperandoCount() {
        return this.modelo.getProcesosEsperando().size();
    }

    public void registrarNuevoProceso(String nombre, String tiempoArribo, String duracion, String memoriaRequerida)
                                      throws NumberFormatException, NullPointerException {
        Integer t = Integer.parseInt(tiempoArribo);
        Integer d = Integer.parseInt(duracion);
        Integer m = Integer.parseInt(memoriaRequerida);
        Proceso nuevoProceso = new Proceso(nombre, t, d, m);
        this.modelo.getProcesosEsperando().add(nuevoProceso);
        this.modelo.ordenarProcesosPorTiempoArribo(this.modelo.getProcesosEsperando());
        setChanged();
        notifyObservers();
    }

    public int getInstanteFinal() {
        return modelo.getReloj().obtenerInstante();
    }

    public void mostrarEvento(Integer instante) {
        evento = modelo.getEventos().get(instante);
    }

    public String obtenerTextoDelEvento() {
        return evento.getCadenas();
    }

    public void iniciarSimulacion() {
        modelo.simular();
    }

    public void iniciarVentanaProceso() {
        Principal.agregarComponenteAlCentro(new ProcesoUI());
    }

    public void iniciarVentanaSimulador() {
        Principal.agregarComponenteAlCentro(new SimuladorUI());
    }

    public void iniciarVentanaResultado() {
        Principal.agregarComponenteAlCentro(new ResultadoUI());
    }

    public void crearNuevaSimulacion() {
        ParticionesDinamicas.setModelo(new Simulador());
    }

}
