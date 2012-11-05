package vistas;

import controladores.Controlador;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.EstadoParticion;
import modelo.Particion;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ParticionTableModel extends AbstractTableModel {

    private String[] columns = {"Identificador", "Dirección Comienzo", "Tamaño", "Estado"};
    private LinkedList<Particion> particiones;

    public ParticionTableModel() {
        super();
        this.particiones = new LinkedList();
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
        return getColumns().length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return getColumns()[columnIndex];
    }

    @Override
    public int getRowCount() {
        return getParticiones().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 :
                return rowIndex;
            case 1 :
                return getParticiones().get(rowIndex).getDireccionComienzo();
            case 2 :
                return getParticiones().get(rowIndex).getTamaño();
            case 3 :
                if(getParticiones().get(rowIndex).getEstado() == EstadoParticion.OCUPADA)
                    return getParticiones().get(rowIndex).getProceso().getNombre();
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

    /**
     * @return the columns
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(String[] columns) {
        this.columns = columns;
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
        this.fireTableDataChanged();
    }

}
