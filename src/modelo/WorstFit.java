package modelo;

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
            ordenarParticionesPorTamaño(particionesLibres);
            while(!particionesLibres.isEmpty()) {
                Particion ultimo = particionesLibres.removeLast();
                if(ultimo.getTamaño() >= memoriaRequerida)
                    return ultimo;
            }
            return null;
        }
        else
            return null;
    }

}
