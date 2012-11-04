package modelo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Mauro Federico Lopez
 */
public class BestFit extends Estrategia {

    public BestFit() {
        super();
    }

    @Override
    protected Particion seleccionarParticion(Integer memoriaRequerida) {
        LinkedList<Particion> particionesLibres = listarParticionesLibres();
        if(particionesLibres != null) {
            ordenarParticionesPorTamañoAsc(particionesLibres);
            Iterator<Particion> i = particionesLibres.iterator();
            while(i.hasNext()){
                Particion primero = i.next();
                if(primero.getTamaño() >= memoriaRequerida)
                    return primero;
            }
            return null;
        }
        else
            return null;
    }

}
