package controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import modelo.*;
import particionesdinamicas.ParticionesDinamicas;
import vistas.*;

/**
 *
 * @author Mauro Federico Lopez
 */
public class Controlador extends Observable {

    private Simulador modelo;
    private Evento evento;

    public Controlador() {
        this.modelo = ParticionesDinamicas.getModelo();
        evento = new Evento(new LinkedList<Particion>(), "");
    }

    public void addObserverModelo(Observer o) {
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
        return this.getInstanteFinal() - 1;
    }

    public LinkedList<Particion> obtenerPaticiones() {
        return evento.getParticiones();
    }

    public String obtenerEvento() {
        return evento.getCadenas();
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
        if(cantProcesos > 0)
            return sumaTiemposRetornos / cantProcesos;
        else
            return 0;
    }

    public void iniciarSimulacion() {
        modelo.simular();
    }

    public void iniciarVentanaProceso() {
        ParticionesDinamicas.getVentanaPrincipal().agregarComponenteAlCentro(new ProcesoUI());
        ParticionesDinamicas.getVentanaSimulacion().setVisible(false);
        ParticionesDinamicas.setVentanaSimulacion(null);
    }

    public void iniciarVentanaSimulador() {
        ParticionesDinamicas.getVentanaPrincipal().agregarComponenteAlCentro(new SimuladorUI());
    }

    public void iniciarVentanaResultado() {
        ParticionesDinamicas.getVentanaPrincipal().agregarComponenteAlCentro(new IndicadoresUI(), new EventosUI());
        ParticionesDinamicas.setVentanaSimulacion(new SimulacionMemoriaUI());
        this.establecerEvento(0);
    }

    public void crearNuevaSimulacion() {
        ParticionesDinamicas.setModelo(new Simulador());
        modelo = ParticionesDinamicas.getModelo();
    }

    public Proceso getProcesoFinalizado(int rowIndex) {
        return this.modelo.getProcesosFinalizados().get(rowIndex);
    }

    public int getProcesoFinalizadoCount() {
        return this.modelo.getProcesosFinalizados().size();
    }

    public void cargarProcesosDelArchivo(File file) throws IOException, NumberFormatException {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            this.modelo.getProcesosEsperando().clear();
            while(in.ready()) {
                StringTokenizer linea = new StringTokenizer(in.readLine());
                while(linea.hasMoreElements()) {
                    String nombre = (String) linea.nextElement();
                    String tiempoArribo = (String) linea.nextElement();
                    String duracion = (String) linea.nextElement();
                    String memoriaRequerida = (String) linea.nextElement();
                    registrarNuevoProceso(nombre, tiempoArribo, duracion, memoriaRequerida);
                }
            }
        }
    }

    public void establecerEvento(Integer instante) {
        this.evento = modelo.getEventos().get(instante);
        setChanged();
        notifyObservers();
    }

    public String obtenerMemoriaFisicaDisponible() {
        return String.valueOf(modelo.getTamañoMemoriaFisica()) + " K";
    }

    public String obtenerEstrategiaSeleccionParticion() {
        return "" + modelo.getEstrategiaSeleccionParticion();
    }

    public String obtenerTiempoSeleccionParticion() {
        return String.valueOf(modelo.getTiempoSeleccionParticion()) + " t";
    }

    public String obtenerTiempoCargaPromedio() {
        return String.valueOf(modelo.getTiempoCargaPromedio()) + " t";
    }

    public String obtenerTiempoLiberacionParticion() {
        return String.valueOf(modelo.getTiempoLiberacionParticion()) + " t";
    }

}
