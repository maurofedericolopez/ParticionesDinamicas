package modelo;

import java.util.Comparator;

/**
 *
 * @author Mauro Federico Lopez
 */
public class TiempoArriboProcesoComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Proceso p1 = (Proceso)o1;
        Proceso p2 = (Proceso)o2;
        return p1.getTiempoArribo() - p2.getTiempoArribo();
    }
    
}
