package vistas;

import controladores.Controlador;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;
import modelo.EstadoParticion;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ParticionTableModel extends AbstractTableModel implements Observer {

    private String[] columns = {"Identificador", "Dirección Comienzo", "Tamaño", "Estado"};
    private Controlador controlador;

    public ParticionTableModel() {
        super();
        this.controlador = new Controlador();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
            case 2:
                return Integer.class;
            case 3:
                return String.class;
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
        return controlador.getParticionCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            /**
            case 0 :
                return controlador.getParticion(rowIndex).getIdentificador();
                */
            case 1 :
                return controlador.getParticion(rowIndex).getDireccionComienzo();
            case 2 :
                return controlador.getParticion(rowIndex).getTamaño();
            case 3 :
                if(controlador.getParticion(rowIndex).getEstado() == EstadoParticion.OCUPADA)
                    return controlador.getParticion(rowIndex).getProceso().getNombre();
                else
                    return "LIBRE";
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
    }

}
