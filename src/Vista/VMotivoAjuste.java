/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author user
 */
public class VMotivoAjuste extends javax.swing.JFrame {

    /**
     * Creates new form VNacionalidad
     */
    public VMotivoAjuste() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaConsulta = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CampoDescripcion = new javax.swing.JTextField();
        CampoCodigo = new javax.swing.JTextField();
        BotonEliminar = new javax.swing.JButton();
        BotonVer = new javax.swing.JButton();
        BotonCancelar = new javax.swing.JButton();
        BotonGuardar = new javax.swing.JButton();
        BotonModificar = new javax.swing.JButton();
        BotonSalir = new javax.swing.JButton();
        BotonImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(650, 520));
        setMinimumSize(new java.awt.Dimension(650, 520));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("MotivoAjuste");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(250, 30, 180, 40);

        tablaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tablaConsulta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 250, 410, 90);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Consulta Ajuste");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 200, 130, 40);

        jLabel3.setText("Descripcion");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 154, 100, 20);

        jLabel4.setText("Codigo");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 110, 60, 20);

        CampoDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDescripcionActionPerformed(evt);
            }
        });
        getContentPane().add(CampoDescripcion);
        CampoDescripcion.setBounds(100, 150, 200, 30);

        CampoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoCodigoActionPerformed(evt);
            }
        });
        getContentPane().add(CampoCodigo);
        CampoCodigo.setBounds(100, 110, 130, 30);

        BotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/eliminar.jpg"))); // NOI18N
        BotonEliminar.setText("ELIMINAR");
        getContentPane().add(BotonEliminar);
        BotonEliminar.setBounds(440, 280, 140, 60);

        BotonVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/ver.jpg"))); // NOI18N
        BotonVer.setText("VER");
        getContentPane().add(BotonVer);
        BotonVer.setBounds(440, 40, 140, 60);

        BotonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/nuevoReg.jpg"))); // NOI18N
        BotonCancelar.setText("CANCELAR");
        getContentPane().add(BotonCancelar);
        BotonCancelar.setBounds(440, 100, 140, 60);

        BotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/agrega.jpg"))); // NOI18N
        BotonGuardar.setText("GUARDAR");
        getContentPane().add(BotonGuardar);
        BotonGuardar.setBounds(440, 160, 140, 60);

        BotonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/modificar1.jpg"))); // NOI18N
        BotonModificar.setText("MODIFICAR");
        getContentPane().add(BotonModificar);
        BotonModificar.setBounds(440, 220, 140, 60);

        BotonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/salir2.jpg"))); // NOI18N
        BotonSalir.setText("SALIR");
        BotonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(BotonSalir);
        BotonSalir.setBounds(440, 400, 140, 60);

        BotonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/imprimir.jpg"))); // NOI18N
        BotonImprimir.setText("IMPRIMIR");
        getContentPane().add(BotonImprimir);
        BotonImprimir.setBounds(440, 340, 140, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDescripcionActionPerformed

    private void CampoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoCodigoActionPerformed

    private void BotonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_BotonSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VMotivoAjuste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VMotivoAjuste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VMotivoAjuste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VMotivoAjuste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VMotivoAjuste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonCancelar;
    public javax.swing.JButton BotonEliminar;
    public javax.swing.JButton BotonGuardar;
    public javax.swing.JButton BotonImprimir;
    public javax.swing.JButton BotonModificar;
    public javax.swing.JButton BotonSalir;
    public javax.swing.JButton BotonVer;
    public javax.swing.JTextField CampoCodigo;
    public javax.swing.JTextField CampoDescripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaConsulta;
    // End of variables declaration//GEN-END:variables
}
