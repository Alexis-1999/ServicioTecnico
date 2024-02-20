/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CBarrio;
import Controlador.CFuncionarios1;
import Controlador.CNacionalidad;
import Modelo.MCliente;
import Modelo.MUsuario;





/**
 *
 * @author Teresa
 */
public class VUsuarios extends javax.swing.JFrame {
     
    /**
     * Creates new form VProducto
     */
    public VUsuarios() {
        initComponents();
    }
    MUsuario modelo = new MUsuario();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaConsulta = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CampoCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ELIMINAR = new javax.swing.JButton();
        NUEVO = new javax.swing.JButton();
        IMPRIMIR = new javax.swing.JButton();
        GUARDAR = new javax.swing.JButton();
        MODIFICAR = new javax.swing.JButton();
        VER = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ComboPermiso = new javax.swing.JComboBox();
        ComboFuncionario = new javax.swing.JComboBox();
        BotonAgregarBarrio = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CampoContraseña = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        CampoUsuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

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
        tablaConsulta.setToolTipText("Consulta de Registros Existentes");
        tablaConsulta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaConsulta.setName(""); // NOI18N
        jScrollPane1.setViewportView(tablaConsulta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 370, 600, 90);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Consulta Usuarios:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 330, 220, 30);

        jLabel3.setText("Codigo:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 120, 50, 20);

        CampoCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CampoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoCodigoActionPerformed(evt);
            }
        });
        getContentPane().add(CampoCodigo);
        CampoCodigo.setBounds(130, 120, 120, 30);

        jLabel4.setText("Contraseña");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 200, 60, 30);

        ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/eliminar.jpg"))); // NOI18N
        ELIMINAR.setText("ELIMINAR");
        ELIMINAR.setToolTipText("Eliminar un registro existente");
        getContentPane().add(ELIMINAR);
        ELIMINAR.setBounds(620, 220, 140, 50);

        NUEVO.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aurelia\\Desktop\\SystemSolution\\src\\imagen2\\nuevoReg.jpg")); // NOI18N
        NUEVO.setText("NUEVO");
        NUEVO.setToolTipText("Cancelar Operacion");
        NUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUEVOActionPerformed(evt);
            }
        });
        getContentPane().add(NUEVO);
        NUEVO.setBounds(620, 70, 140, 50);

        IMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/imprimir.jpg"))); // NOI18N
        IMPRIMIR.setText("IMPRIMIR  ");
        IMPRIMIR.setToolTipText("Imprimir Listado");
        getContentPane().add(IMPRIMIR);
        IMPRIMIR.setBounds(620, 270, 140, 50);

        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/agrega.jpg"))); // NOI18N
        GUARDAR.setText("GUARDAR ");
        GUARDAR.setToolTipText("Guardar Registros");
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });
        getContentPane().add(GUARDAR);
        GUARDAR.setBounds(620, 120, 140, 50);

        MODIFICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/modificar1.jpg"))); // NOI18N
        MODIFICAR.setText("MODIFICAR  ");
        MODIFICAR.setToolTipText("Modificar un registro existente");
        getContentPane().add(MODIFICAR);
        MODIFICAR.setBounds(620, 170, 140, 50);

        VER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/ver.jpg"))); // NOI18N
        VER.setText("VER ");
        VER.setToolTipText("Consultar Datos Existentes");
        VER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VERActionPerformed(evt);
            }
        });
        getContentPane().add(VER);
        VER.setBounds(620, 20, 140, 50);

        SALIR.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aurelia\\Desktop\\SystemSolution\\src\\imagen2\\salir2.jpg")); // NOI18N
        SALIR.setText("SALIR");
        SALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALIRActionPerformed(evt);
            }
        });
        getContentPane().add(SALIR);
        SALIR.setBounds(620, 320, 140, 50);

        jLabel7.setText("Funcionario");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 240, 60, 40);

        ComboPermiso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));
        ComboPermiso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboPermisoMouseClicked(evt);
            }
        });
        ComboPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboPermisoActionPerformed(evt);
            }
        });
        getContentPane().add(ComboPermiso);
        ComboPermiso.setBounds(130, 280, 180, 30);

        ComboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sleleccione..." }));
        ComboFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(ComboFuncionario);
        ComboFuncionario.setBounds(130, 240, 170, 30);

        BotonAgregarBarrio.setText("...");
        BotonAgregarBarrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarBarrioActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregarBarrio);
        BotonAgregarBarrio.setBounds(320, 280, 40, 30);

        jLabel10.setText("Permiso");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(50, 280, 60, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("Usuarios");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(350, 20, 220, 30);

        CampoContraseña.setText("jPasswordField1");
        getContentPane().add(CampoContraseña);
        CampoContraseña.setBounds(130, 200, 170, 30);

        jLabel5.setText("Usuario");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 160, 70, 30);

        CampoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(CampoUsuario);
        CampoUsuario.setBounds(130, 160, 170, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoCodigoActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        dispose();
    }//GEN-LAST:event_SALIRActionPerformed

    private void ComboPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboPermisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboPermisoActionPerformed

    private void ComboPermisoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboPermisoMouseClicked
        //  ComboNacionalidad.removeAllItems();
            //combo
          this.ComboPermiso.setModel(this.modelo.getListaPermiso());
    }//GEN-LAST:event_ComboPermisoMouseClicked

    private void BotonAgregarBarrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarBarrioActionPerformed
         new CFuncionarios1(new VFuncionario1()).iniciar();
    }//GEN-LAST:event_BotonAgregarBarrioActionPerformed

    private void NUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUEVOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NUEVOActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
         // this.ComboFuncionario.setModel(this.modelo.getListaBarrio());
           this.ComboFuncionario.setModel(this.modelo.getListaFuncionario());
            this.ComboPermiso.setModel(this.modelo.getListaPermiso());
    }//GEN-LAST:event_formWindowActivated

    private void VERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VERActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GUARDARActionPerformed

    private void IMPRIMIRCLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMPRIMIRCLIENTEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IMPRIMIRCLIENTEActionPerformed

    private void ComboFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboFuncionarioActionPerformed

    private void CampoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoUsuarioActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonAgregarBarrio;
    public javax.swing.JTextField CampoCodigo;
    public javax.swing.JPasswordField CampoContraseña;
    public javax.swing.JTextField CampoUsuario;
    public javax.swing.JComboBox ComboFuncionario;
    public javax.swing.JComboBox ComboPermiso;
    public javax.swing.JButton ELIMINAR;
    public javax.swing.JButton GUARDAR;
    public javax.swing.JButton IMPRIMIR;
    public javax.swing.JButton MODIFICAR;
    public javax.swing.JButton NUEVO;
    public javax.swing.JButton SALIR;
    public javax.swing.JButton VER;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaConsulta;
    // End of variables declaration//GEN-END:variables
}
