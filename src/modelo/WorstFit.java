package modelo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Mauro Federico Lopez
 */
public class WorstFit extends Estrategia {

    public WorstFit() {
        super();
    }

    @Override
    protected Particion seleccionarParticion(Integer memoriaRequerida) {
        LinkedList<Particion> particionesLibres = listarParticionesLibres();
        if(particionesLibres != null) {
            ordenarParticionesPorTamañoDesc(particionesLibres);
            Iterator<Particion> i = particionesLibres.iterator();
            while(i.hasNext()){
                Particion ultimo = i.next();
                if(ultimo.getTamaño() >= memoriaRequerida)
                    return ultimo;
            }
            return null;
        }
        else
            return null;
    }

    @Override
    public String toString() {
        return "Worst-Fit";
    }

}
