/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;




import Controlador.CBarrio;
import Controlador.CCliente;
import Controlador.CEntidadesEmisoras;
import Controlador.CTipoTarjeta;
import Modelo.MEntidadesEmisoras;
import Modelo.MTipoTarjeta;







/**
 *
 * @author Teresa
 */
public class VTarjeta extends javax.swing.JFrame {
     
    /**
     * Creates new form VProducto
     */
    public VTarjeta() {
        initComponents();
    }
    MTipoTarjeta modelo = new MTipoTarjeta();
   // MEntidadesEmisoras modelo = new MEntidadesEmisoras();
     
    

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
        jLabel4 = new javax.swing.JLabel();
        CampoNroTarjeta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CampoMonto = new javax.swing.JTextField();
        ELIMINAR = new javax.swing.JButton();
        NUEVO = new javax.swing.JButton();
        IMPRIMIR = new javax.swing.JButton();
        GUARDAR = new javax.swing.JButton();
        MODIFICAR = new javax.swing.JButton();
        VER = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        CampoFechaEmision = new javax.swing.JTextField();
        SALIR = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ComboTipotarjeta = new javax.swing.JComboBox();
        ComboEntidadEmisora = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        CampoFechaCobro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaConsulta = new javax.swing.JTable();
        ComboCliente = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        BotonAgregarBarrio1 = new javax.swing.JButton();
        BotonAgregarBarrio2 = new javax.swing.JButton();

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
        jLabel2.setText("Consulta Funcionario:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(310, 320, 170, 40);

        jLabel3.setText("Codigo:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 130, 50, 20);

        CampoCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CampoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoCodigoActionPerformed(evt);
            }
        });
        getContentPane().add(CampoCodigo);
        CampoCodigo.setBounds(110, 120, 160, 30);

        jLabel4.setText("Nro_Tarjeta");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 160, 60, 30);

        CampoNroTarjeta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(CampoNroTarjeta);
        CampoNroTarjeta.setBounds(110, 160, 160, 30);

        jLabel5.setText("Monto");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 210, 60, 20);

        CampoMonto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(CampoMonto);
        CampoMonto.setBounds(110, 200, 160, 30);

        ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/eliminar.jpg"))); // NOI18N
        ELIMINAR.setText("ELIMINAR ");
        ELIMINAR.setToolTipText("Eliminar un registro existente");
        ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELIMINARActionPerformed(evt);
            }
        });
        getContentPane().add(ELIMINAR);
        ELIMINAR.setBounds(80, 550, 170, 50);

        NUEVO.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aurelia\\Desktop\\SystemSolution\\src\\imagen2\\nuevoReg.jpg")); // NOI18N
        NUEVO.setText("NUEVO  ");
        NUEVO.setToolTipText("Cancelar Operacion");
        NUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUEVOActionPerformed(evt);
            }
        });
        getContentPane().add(NUEVO);
        NUEVO.setBounds(260, 490, 170, 50);

        IMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/imprimir.jpg"))); // NOI18N
        IMPRIMIR.setText("IMPRIMIR  ");
        IMPRIMIR.setToolTipText("Imprimir Listado");
        getContentPane().add(IMPRIMIR);
        IMPRIMIR.setBounds(260, 550, 170, 50);

        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/agrega.jpg"))); // NOI18N
        GUARDAR.setText("GUARDAR ");
        GUARDAR.setToolTipText("Guardar Registros");
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });
        getContentPane().add(GUARDAR);
        GUARDAR.setBounds(440, 550, 170, 50);

        MODIFICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/modificar1.jpg"))); // NOI18N
        MODIFICAR.setText("MODIFICAR  ");
        MODIFICAR.setToolTipText("Modificar un registro existente");
        getContentPane().add(MODIFICAR);
        MODIFICAR.setBounds(440, 490, 170, 50);

        VER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen2/ver.jpg"))); // NOI18N
        VER.setText("VER ");
        VER.setToolTipText("Consultar Datos Existentes");
        getContentPane().add(VER);
        VER.setBounds(80, 490, 170, 50);

        jLabel9.setText("Fecha_Emision");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(10, 250, 80, 14);

        CampoFechaEmision.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CampoFechaEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoFechaEmisionActionPerformed(evt);
            }
        });
        getContentPane().add(CampoFechaEmision);
        CampoFechaEmision.setBounds(110, 240, 160, 30);

        SALIR.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aurelia\\Desktop\\SystemSolution\\src\\imagen2\\salir2.jpg")); // NOI18N
        SALIR.setText("SALIR");
        SALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALIRActionPerformed(evt);
            }
        });
        getContentPane().add(SALIR);
        SALIR.setBounds(620, 520, 170, 50);

        jLabel7.setText("Entidades_Emisoras");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(300, 160, 110, 40);

        ComboTipotarjeta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));
        ComboTipotarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboTipotarjetaMouseClicked(evt);
            }
        });
        ComboTipotarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTipotarjetaActionPerformed(evt);
            }
        });
        getContentPane().add(ComboTipotarjeta);
        ComboTipotarjeta.setBounds(400, 110, 190, 30);

        ComboEntidadEmisora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sleleccione..." }));
        ComboEntidadEmisora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboEntidadEmisoraActionPerformed(evt);
            }
        });
        getContentPane().add(ComboEntidadEmisora);
        ComboEntidadEmisora.setBounds(400, 160, 190, 30);

        jLabel10.setText("TipoTarjetas");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(320, 110, 70, 40);

        CampoFechaCobro.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CampoFechaCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoFechaCobroActionPerformed(evt);
            }
        });
        getContentPane().add(CampoFechaCobro);
        CampoFechaCobro.setBounds(110, 290, 160, 30);

        jLabel11.setText("Fecha_Cobro");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(20, 290, 70, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("TARJETAS");
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
        jScrollPane2.setBounds(10, 360, 950, 100);

        ComboCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sleleccione..." }));
        getContentPane().add(ComboCliente);
        ComboCliente.setBounds(400, 210, 190, 30);

        jLabel12.setText("Clientes");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(320, 210, 60, 40);

        BotonAgregarBarrio1.setText("...");
        BotonAgregarBarrio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarBarrio1ActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregarBarrio1);
        BotonAgregarBarrio1.setBounds(600, 210, 40, 30);

        BotonAgregarBarrio2.setText("...");
        BotonAgregarBarrio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarBarrio2ActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregarBarrio2);
        BotonAgregarBarrio2.setBounds(600, 160, 40, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoCodigoActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        dispose();
    }//GEN-LAST:event_SALIRActionPerformed

    private void ComboTipotarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipotarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboTipotarjetaActionPerformed

    private void ComboTipotarjetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboTipotarjetaMouseClicked
       // new CCargos(new VCargos()).iniciar();
    }//GEN-LAST:event_ComboTipotarjetaMouseClicked

    private void NUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUEVOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NUEVOActionPerformed

    private void CampoFechaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoFechaEmisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoFechaEmisionActionPerformed

    private void CampoFechaCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoFechaCobroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoFechaCobroActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
         
    }//GEN-LAST:event_formWindowActivated

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELIMINARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELIMINARActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GUARDARActionPerformed

    private void BotonAgregarBarrio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarBarrio1ActionPerformed
        new CCliente(new VCliente()) . iniciar ();
    }//GEN-LAST:event_BotonAgregarBarrio1ActionPerformed

    private void BotonAgregarBarrio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarBarrio2ActionPerformed
      new CEntidadesEmisoras(new VEntidadesEmisoras()).iniciar ();
    }//GEN-LAST:event_BotonAgregarBarrio2ActionPerformed

    private void ComboEntidadEmisoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboEntidadEmisoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboEntidadEmisoraActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonAgregarBarrio1;
    public javax.swing.JButton BotonAgregarBarrio2;
    public javax.swing.JTextField CampoCodigo;
    public javax.swing.JTextField CampoFechaCobro;
    public javax.swing.JTextField CampoFechaEmision;
    public javax.swing.JTextField CampoMonto;
    public javax.swing.JTextField CampoNroTarjeta;
    public javax.swing.JComboBox ComboCliente;
    public javax.swing.JComboBox ComboEntidadEmisora;
    public javax.swing.JComboBox ComboTipotarjeta;
    public javax.swing.JButton ELIMINAR;
    public javax.swing.JButton GUARDAR;
    public javax.swing.JButton IMPRIMIR;
    public javax.swing.JButton MODIFICAR;
    public javax.swing.JButton NUEVO;
    public javax.swing.JButton SALIR;
    public javax.swing.JButton VER;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tablaConsulta;
    // End of variables declaration//GEN-END:variables
}
