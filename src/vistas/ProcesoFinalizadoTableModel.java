package vistas;

import controladores.Controlador;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ProcesoFinalizadoTableModel extends AbstractTableModel {

    private String[] columns = {"Nombre", "Tiempo Retorno"};
    private Controlador controlador;

    public ProcesoFinalizadoTableModel() {
        super();
        this.controlador = new Controlador();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
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
        return controlador.getProcesoFinalizadoCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 :
                return controlador.getProcesoFinalizado(rowIndex).getNombre();
            case 1 :
                return controlador.getProcesoFinalizado(rowIndex).getTiempoRetorno();
            default :
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
