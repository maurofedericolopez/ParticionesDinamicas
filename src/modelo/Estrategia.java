package modelo;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import particionesdinamicas.ParticionesDinamicas;

/**
 *
 * @author Mauro Federico Lopez
 */
public abstract class Estrategia {

    protected LinkedList<Particion> particiones;

    public Estrategia() {
        this.particiones = new LinkedList();
    }

    protected abstract Particion seleccionarParticion(Integer memoriaRequerida);

    public void asignarParticion(Proceso proceso, Integer instante) {
        Particion p = seleccionarParticion(proceso.getMemoriaRequerida());
        Integer tamañoParticion = p.getTamaño();
        p.setTamaño(proceso.getMemoriaRequerida());
        p.setProceso(proceso);
        p.setEstado(EstadoParticion.OCUPADA);
        proceso.setTiempoCarga(instante);
        if(tamañoParticion > p.getTamaño()) {
            Integer direccionComienzo = p.getDireccionComienzo() + p.getTamaño();
            Integer nuevoTamañoParticion = tamañoParticion - proceso.getMemoriaRequerida();
            this.particiones.add(new Particion(direccionComienzo, nuevoTamañoParticion));
            ordenarParticionesPorDireccionComienzo(particiones);
        }
    }

    public void liberarParticion(Proceso proceso) {
        Iterator<Particion> i = listarParticionesOcupadas().iterator();
        while(i.hasNext()) {
            Particion p = i.next();
            if(p.getProceso().equals(proceso)){
                p.setEstado(EstadoParticion.LIBRE);
                p.setProceso(null);
                break;
            }
        }
        compactarMemoria();
    }

    public Integer calcularIndiceFragmentacionExterna() {
        Integer suma = 0;
        if(!listarParticionesLibres().isEmpty()) {
            LinkedList<Particion> particionesLibres = listarParticionesLibres();
            while(!particionesLibres.isEmpty()) {
                Particion p = particionesLibres.removeFirst();
                suma += p.getTamaño();
            }
                
        }
        return suma;
    }

    protected void ordenarParticionesPorDireccionComienzo(LinkedList<Particion> particiones) {
        Collections.sort(particiones, new DireccionComienzoParticionComparator());
    }

    protected void ordenarParticionesPorTamañoAsc(LinkedList<Particion> particiones) {
        Collections.sort(particiones, new TamañoParticionAscComparator());
    }

    protected void ordenarParticionesPorTamañoDesc(LinkedList<Particion> particionesLibres) {
        Collections.sort(particiones, new TamañoParticionDescComparator());
    }

    protected LinkedList<Particion> listarParticionesLibres() {
        Iterator<Particion> i = particiones.iterator();
        LinkedList<Particion> particionesLibres = new LinkedList();
        while(i.hasNext()) {
            Particion p = i.next();
            if(p.getEstado().equals(EstadoParticion.LIBRE))
                particionesLibres.add(p);
        }
        return particionesLibres;
    }

    protected LinkedList<Particion> listarParticionesOcupadas() {
        Iterator<Particion> i = particiones.iterator();
        LinkedList<Particion> particionesOcupadas = new LinkedList();
        while(i.hasNext()) {
            Particion p = i.next();
            if(p.getEstado().equals(EstadoParticion.OCUPADA))
                particionesOcupadas.add(p);
        }
        return particionesOcupadas;
    }

    public LinkedList<Proceso> listarProcesosFinalizados(Integer instante) {
        Iterator<Particion> i = listarParticionesOcupadas().iterator();
        LinkedList<Proceso> procesosFinalizados = new LinkedList();
        while(i.hasNext()) {
            Particion p = i.next();
            if((instante - p.getProceso().getTiempoCarga()) >= p.getProceso().getDuracionTrabajo())
                procesosFinalizados.add(p.getProceso());
        }
        return procesosFinalizados;
    }

    /**
     * Compacta la memoria unificando particiones contiguas que tenga estado libre.
     */
    public void compactarMemoria() {
        while(hayParticionesParaCompactar())
            for(int i = 0; i < particiones.size() - 1; i++) {
                Particion p1 = particiones.get(i);
                Particion p2 = particiones.get(i + 1);
                if(p1.getEstado().equals(EstadoParticion.LIBRE) && p2.getEstado().equals(EstadoParticion.LIBRE)) {
                    p2.setTamaño(p1.getTamaño() + p2.getTamaño());
                    p2.setDireccionComienzo(p1.getDireccionComienzo());
                    particiones.remove(p1);
                }
            }
    }

    public Boolean hayEspacioParaProceso(Integer memoriaRequerida) {
        LinkedList<Particion> particionesLibres = listarParticionesLibres();
        if(particionesLibres != null) {
            Collections.sort(particionesLibres, new TamañoParticionAscComparator());
            while(!particionesLibres.isEmpty()) {
                Particion primero = particionesLibres.removeFirst();
                if(primero.getTamaño() >= memoriaRequerida)
                    return true;
            }
            return false;
        }
        else
            return false;
    }

    public Boolean hayParticionesParaLiberar(Integer instante) {
        LinkedList<Particion> particionesOcupadas = listarParticionesOcupadas();
        if(particionesOcupadas != null) {
            while(!particionesOcupadas.isEmpty()) {
                Particion p = particionesOcupadas.removeFirst();
                if((instante - p.getProceso().getTiempoCarga()) >= p.getProceso().getDuracionTrabajo())
                    return true;
            }
            return false;
        }
        else
            return false;
    }

    public Boolean hayParticionesOcupadas() {
        return !listarParticionesOcupadas().isEmpty();
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

    public void crearPrimeraParticion() {
        Integer tamañoMemoriaFisicaDisponible = ParticionesDinamicas.getModelo().getTamañoMemoriaFisica();
        Particion primeraParticion = new Particion(0, tamañoMemoriaFisicaDisponible);
        this.particiones.add(primeraParticion);
    }

    public LinkedList<Particion> obtenerParticionesActuales() {
        LinkedList<Particion> nuevasParticiones = new LinkedList();
        Iterator<Particion> i = particiones.iterator();
        while(i.hasNext()) {
            Particion p = i.next();
            Particion particionNueva = new Particion(p.getDireccionComienzo(), p.getTamaño());
            particionNueva.setProceso(p.getProceso());
            particionNueva.setEstado(p.getEstado());
            nuevasParticiones.add(particionNueva);
        }
        this.ordenarParticionesPorDireccionComienzo(nuevasParticiones);
        return nuevasParticiones;
    }

    private Boolean hayParticionesParaCompactar() {
        for(int i = 0; i < particiones.size() - 1; i++) {
            Particion p1 = particiones.get(i);
            Particion p2 = particiones.get(i + 1);
            if(p1.getEstado().equals(EstadoParticion.LIBRE) && p2.getEstado().equals(EstadoParticion.LIBRE)) {
                return true;
            }
        }
        return false;
    }

}
