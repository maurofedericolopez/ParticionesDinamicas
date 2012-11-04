package controladores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observer;
import modelo.*;
import particionesdinamicas.ParticionesDinamicas;
import vistas.EventosUI;
import vistas.IndicadoresUI;
import vistas.ProcesoUI;
import vistas.SimuladorUI;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Controlador {

    private Simulador modelo;

    public Controlador() {
        this.modelo = ParticionesDinamicas.getModelo();
    }

    public void addObserver(Observer o) {
        this.modelo.addObserver(o);
    }

    public void cargarDatosSimulador(String TMF, String TSP, String TCP, String TLP, Estrategia ESP) throws NumberFormatException, NullPointerException {
        this.modelo.setTamañoMemoriaFisica(Integer.parseInt(TMF));
        this.modelo.setTiempoSeleccionParticion(Integer.parseInt(TSP));
        this.modelo.setTiempoCargaPromedio(Integer.parseInt(TCP));
        this.modelo.setTiempoLiberacionParticion(Integer.parseInt(TLP));
        this.modelo.setEstrategiaSeleccionParticion(ESP);
        ESP.crearPrimeraParticion();
    }

    public void eliminarProceso(int rowIndex) throws Exception {
        if(rowIndex >= 0) {
            this.modelo.getProcesosEsperando().remove(rowIndex);
            this.modelo.notificarCambio();
        }
        else
            throw new Exception();
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
        Proceso nuevoProceso = new Proceso(nombre.toUpperCase(), t, d, m);
        this.modelo.getProcesosEsperando().add(nuevoProceso);
        this.modelo.ordenarProcesosPorTiempoArribo(this.modelo.getProcesosEsperando());
        this.modelo.notificarCambio();
    }

    public Integer getInstanteFinal() {
        return modelo.getReloj().obtenerInstante();
    }

    public Integer obtenerTiempoRetornoTanda() {
        return modelo.getReloj().obtenerInstante() - 1;
    }

    public LinkedList<Particion> obtenerPaticiones(Integer instante) {
        return modelo.getEventos().get(instante).getParticiones();
    }

    public String obtenerEvento(Integer instante) {
        return modelo.getEventos().get(instante).getCadenas();
    }

    public Integer obtenerIndiceFragmentacionExterna() {
        return modelo.getIndiceFragmentacionExterna();
    }

    public Integer obtenerTiempoMedioRetorno() {
        Integer sumaTiemposRetornos = 0;
        Integer cantProcesos = 0;
        Iterator<Proceso> i = modelo.getProcesosFinalizados().iterator();
        while(i.hasNext()) {
            Proceso p = i.next();
            if(p.getTiempoRetorno() > 0) {
                cantProcesos++;
                sumaTiemposRetornos += p.getTiempoRetorno();
            }
        }
        return sumaTiemposRetornos / cantProcesos;
    }

    public void iniciarSimulacion() {
        modelo.simular();
    }

    public void iniciarVentanaProceso() {
        ParticionesDinamicas.getVentanaPrincipal().agregarComponenteAlCentro(new ProcesoUI());
    }

    public void iniciarVentanaSimulador() {
        ParticionesDinamicas.getVentanaPrincipal().agregarComponenteAlCentro(new SimuladorUI());
    }

    public void iniciarVentanaResultado() {
        ParticionesDinamicas.getVentanaPrincipal().agregarComponenteAlCentro(new IndicadoresUI(), new EventosUI());
    }

    public void crearNuevaSimulacion() {
        ParticionesDinamicas.setModelo(new Simulador());
    }

    public Proceso getProcesoFinalizado(int rowIndex) {
        return this.modelo.getProcesosFinalizados().get(rowIndex);
    }

    public int getProcesoFinalizadoCount() {
        return this.modelo.getProcesosFinalizados().size();
    }

}
