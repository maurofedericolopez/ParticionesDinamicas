package modelo;

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
            while(!particionesLibres.isEmpty()) {
                Particion primero = particionesLibres.removeFirst();
                if(primero.getTamaño() >= memoriaRequerida)
                    return primero;
            }
            return null;
        }
        else
            return null;
    }

    private LinkedList<Particion> listarParticiones() {
        LinkedList<Particion> particionesLibres = listarParticionesLibres();
        while(!particionesLibres.isEmpty()) {
            Particion p = particionesLibres.getFirst();
            if(!p.getDireccionComienzo().equals(direccionComienzoUltimaParticionAsignada)) {
                particionesLibres.addLast(particionesLibres.removeFirst());
            }
            else
                break;
        }
        return particionesLibres;
    }

}
