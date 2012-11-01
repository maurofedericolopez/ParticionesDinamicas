package vistas;

import controladores.Controlador;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Mauro Federico Lopez
 */
public class ResultadoUI extends javax.swing.JPanel implements Observer {

    Controlador controlador;

    /**
     * Creates new form ResultadoUI
     */
    public ResultadoUI() {
        initComponents();
        controlador = new Controlador();
        controlador.addObserver(this);
        slider.setMaximum(controlador.getInstanteFinal());
        slider.setMinimum(0);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controlador.mostrarEvento(slider.getValue());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        botonNuevaSimulacion = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        slider = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        jspArea = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jspTabla = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(550, 300));
        setMinimumSize(new java.awt.Dimension(550, 300));
        setPreferredSize(new java.awt.Dimension(550, 300));
        setLayout(new java.awt.BorderLayout());

        botonNuevaSimulacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        botonNuevaSimulacion.setText("Nueva Simulación");
        botonNuevaSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaSimulacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(427, Short.MAX_VALUE)
                .addComponent(botonNuevaSimulacion)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonNuevaSimulacion)
        );

        add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(slider, java.awt.BorderLayout.PAGE_END);

        jspArea.setMaximumSize(new java.awt.Dimension(186, 100));
        jspArea.setMinimumSize(new java.awt.Dimension(186, 100));
        jspArea.setPreferredSize(new java.awt.Dimension(186, 100));

        areaTexto.setColumns(20);
        areaTexto.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        areaTexto.setRows(5);
        jspArea.setViewportView(areaTexto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspArea, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspArea, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel1, java.awt.BorderLayout.LINE_START);

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaEventos.setMaximumSize(new java.awt.Dimension(60, 64));
        tablaEventos.setPreferredSize(new java.awt.Dimension(64, 64));
        jspTabla.setViewportView(tablaEventos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel2, java.awt.BorderLayout.LINE_END);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevaSimulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaSimulacionActionPerformed
        controlador.crearNuevaSimulacion();
        controlador.iniciarVentanaProceso();
    }//GEN-LAST:event_botonNuevaSimulacionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JButton botonNuevaSimulacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jspArea;
    private javax.swing.JScrollPane jspTabla;
    private javax.swing.JSlider slider;
    private javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        areaTexto.setText(controlador.obtenerTextoDelEvento());
    }
}