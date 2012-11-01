package modelo;

import java.util.LinkedList;

/**
 *
 * @author Mauro Federico Lopez
 */
public class FirstFit extends Estrategia {

    public FirstFit() {
        super();
    }

    @Override
    protected Particion seleccionarParticion(Integer memoriaRequerida) {
        LinkedList<Particion> particionesLibres = listarParticionesLibres();
        if(particionesLibres != null) {
            ordenarParticionesPorDireccionComienzo(particionesLibres);
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

}
