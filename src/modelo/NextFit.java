package modelo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Mauro Federico Lopez
 */
public class NextFit extends Estrategia {

    private Integer direccionComienzoUltimaParticionAsignada;

    public NextFit() {
        super();
        this.direccionComienzoUltimaParticionAsignada = 0;
    }

    @Override
    protected Particion seleccionarParticion(Integer memoriaRequerida) {
        LinkedList<Particion> particionesLibres = listarParticiones();
        if(particionesLibres != null) {
            Iterator<Particion> i = particionesLibres.iterator();
            while(i.hasNext()) {
                Particion primero = i.next();
                if(primero.getTamaño() >= memoriaRequerida) {
                    direccionComienzoUltimaParticionAsignada = primero.getDireccionComienzo();
                    return primero;
                }
            }
            return null;
        }
        else
            return null;
    }

    private LinkedList<Particion> listarParticiones() {
        LinkedList<Particion> particionesLibres = listarParticionesLibres();
        this.ordenarParticionesPorDireccionComienzo(particionesLibres);
        Integer i = particionesLibres.getLast().getDireccionComienzo();
        while(!particionesLibres.isEmpty()) {
            Particion p = particionesLibres.getFirst();
            if(p.getDireccionComienzo() < direccionComienzoUltimaParticionAsignada && i != p.getDireccionComienzo()) {
                particionesLibres.addLast(particionesLibres.removeFirst());
                System.out.println("" + p.getDireccionComienzo() + " < " + direccionComienzoUltimaParticionAsignada);
            }
            else
                break;
        }
        return particionesLibres;
    }

    @Override
    public String toString() {
        return "Next-Fit";
    }

}
