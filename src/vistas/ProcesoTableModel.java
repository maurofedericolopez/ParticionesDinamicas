package vistas;

import controladores.Controlador;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ProcesoTableModel extends AbstractTableModel implements Observer {

    private String[] columns = {"Nombre", "Arribo[seg]", "Duracion[seg]", "Memoria Req[k]"};
    private Controlador controlador;
    private int columnCount;

    public ProcesoTableModel() {
        super();
        this.controlador = new Controlador();
        this.controlador.addObserver(this);
        System.out.println(""+controlador.countObservers());
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public int getRowCount() {
        columnCount = controlador.getProcesoEsperandoCount();
        return controlador.getProcesoEsperandoCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 :
                return controlador.getProcesoEsperando(rowIndex).getNombre();
            case 1 :
                return controlador.getProcesoEsperando(rowIndex).getTiempoArribo();
            case 2 :
                return controlador.getProcesoEsperando(rowIndex).getDuracionTrabajo();
            case 3 :
                return controlador.getProcesoEsperando(rowIndex).getMemoriaRequerida();
            default :
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.fireTableDataChanged();
        this.fireTableStructureChanged();
    }

}
