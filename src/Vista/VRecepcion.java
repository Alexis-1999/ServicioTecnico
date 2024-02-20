/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;



import Controlador.CBarrio;
import Controlador.CEquipo;
import Modelo.MBarrio;
import Modelo.MCargos;







/**
 *
 * @author Teresa
 */
public class VRecepcion extends javax.swing.JFrame {
     
    /**
     * Creates new form VProducto
     */
    public VRecepcion() {
        initComponents();
    }
    MBarrio modelo = new MBarrio();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CampoCodigo = new javax.swing.JTextField();
        ELIMINAR = new javax.swing.JButton();
        NUEVO = new javax.swing.JButton();
        IMPRIMIR = new javax.swing.JButton();
        GUARDAR = new javax.swing.JButton();
        MODIFICAR = new javax.swing.JButton();
        VER = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ComboEquipo = new javax.swing.JComboBox();
        CampoFecha = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaConsulta = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        CampoDescripcion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        ComboUsuario = new javax.swing.JComboBox();
        BotonAgregarNacionalidad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("CONSULTA :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 370, 90, 30);

        jLabel3.setText("CODIGO");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 120, 50, 20);

        CampoCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CampoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoCodigoActionPerformed(evt);
            }
        });
        getContentPane().add(CampoCodigo);
        CampoCodigo.setBounds(150, 120, 120, 30);

        ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/eliminar.jpg"))); // NOI18N
        ELIMINAR.setText("ELIMINAR ");
        ELIMINAR.setToolTipText("Eliminar un registro existente");
        getContentPane().add(ELIMINAR);
        ELIMINAR.setBounds(580, 230, 140, 50);

        NUEVO.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aurelia\\Desktop\\SystemSolution\\src\\imagen2\\nuevoReg.jpg")); // NOI18N
        NUEVO.setText("NUEVO  ");
        NUEVO.setToolTipText("Cancelar Operacion");
        NUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUEVOActionPerformed(evt);
            }
        });
        getContentPane().add(NUEVO);
        NUEVO.setBounds(580, 80, 140, 50);

        IMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/imprimir.jpg"))); // NOI18N
        IMPRIMIR.setText("IMPRIMIR  ");
        IMPRIMIR.setToolTipText("Imprimir Listado");
        getContentPane().add(IMPRIMIR);
        IMPRIMIR.setBounds(580, 280, 140, 50);

        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/agrega.jpg"))); // NOI18N
        GUARDAR.setText("GUARDAR ");
        GUARDAR.setToolTipText("Guardar Registros");
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });
        getContentPane().add(GUARDAR);
        GUARDAR.setBounds(580, 130, 140, 50);

        MODIFICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/modificar1.jpg"))); // NOI18N
        MODIFICAR.setText("MODIFICAR  ");
        MODIFICAR.setToolTipText("Modificar un registro existente");
        getContentPane().add(MODIFICAR);
        MODIFICAR.setBounds(580, 180, 140, 50);

        VER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/ver.jpg"))); // NOI18N
        VER.setText("VER ");
        VER.setToolTipText("Consultar Datos Existentes");
        getContentPane().add(VER);
        VER.setBounds(580, 30, 140, 50);

        SALIR.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aurelia\\Desktop\\SystemSolution\\src\\imagen2\\salir2.jpg")); // NOI18N
        SALIR.setText("SALIR");
        SALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALIRActionPerformed(evt);
            }
        });
        getContentPane().add(SALIR);
        SALIR.setBounds(580, 330, 140, 50);

        jLabel7.setText("EQUIPOS");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 280, 100, 40);

        ComboEquipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sleleccione..." }));
        getContentPane().add(ComboEquipo);
        ComboEquipo.setBounds(150, 290, 200, 30);

        CampoFecha.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CampoFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoFechaActionPerformed(evt);
            }
        });
        getContentPane().add(CampoFecha);
        CampoFecha.setBounds(150, 200, 230, 30);

        jLabel11.setText("FECHA");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(40, 200, 50, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("RECEPCION");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(350, 20, 220, 30);

        tablaConsulta.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaConsulta);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 400, 540, 100);

        jLabel12.setText("DESCRIPCION");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(40, 160, 90, 30);

        CampoDescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CampoDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoDescripcionActionPerformed(evt);
            }
        });
        getContentPane().add(CampoDescripcion);
        CampoDescripcion.setBounds(150, 160, 230, 30);

        jLabel13.setText("USUARIO");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(40, 240, 60, 40);

        ComboUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));
        ComboUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboUsuarioMouseClicked(evt);
            }
        });
        ComboUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(ComboUsuario);
        ComboUsuario.setBounds(150, 240, 200, 30);

        BotonAgregarNacionalidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BotonAgregarNacionalidad.setText("...");
        BotonAgregarNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarNacionalidadActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregarNacionalidad);
        BotonAgregarNacionalidad.setBounds(360, 290, 40, 31);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoCodigoActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        dispose();
    }//GEN-LAST:event_SALIRActionPerformed

    private void NUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUEVOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NUEVOActionPerformed

    private void CampoFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoFechaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
         
    }//GEN-LAST:event_formWindowActivated

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELIMINARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELIMINARActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GUARDARActionPerformed

    private void CampoDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDescripcionActionPerformed

    private void ComboUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboUsuarioMouseClicked

    private void ComboUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboUsuarioActionPerformed

    private void BotonAgregarNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarNacionalidadActionPerformed
        new CEquipo(new VEquipo()).iniciar();
    }//GEN-LAST:event_BotonAgregarNacionalidadActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarNacionalidad;
    public javax.swing.JTextField CampoCodigo;
    public javax.swing.JTextField CampoDescripcion;
    public javax.swing.JTextField CampoFecha;
    public javax.swing.JComboBox ComboEquipo;
    public javax.swing.JComboBox ComboUsuario;
    public javax.swing.JButton ELIMINAR;
    public javax.swing.JButton GUARDAR;
    public javax.swing.JButton IMPRIMIR;
    public javax.swing.JButton MODIFICAR;
    public javax.swing.JButton NUEVO;
    public javax.swing.JButton SALIR;
    public javax.swing.JButton VER;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tablaConsulta;
    // End of variables declaration//GEN-END:variables
}
