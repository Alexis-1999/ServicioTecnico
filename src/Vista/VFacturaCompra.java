/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package Vista;


import Controlador.CFacturaCompra;
import javax.swing.JOptionPane;

public class VFacturaCompra extends javax.swing.JFrame {

    /** Creates new form VFactVentas */
    public VFacturaCompra() {
        initComponents();
        CFacturaCompra mm=new CFacturaCompra(this);
        mm.limpiar();
       mm.iniciar();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcodfact = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        ComboTipoFactura = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnagregar_detalle = new javax.swing.JButton();
        btneliminar_detalle = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaventas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cboInsumos = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        AgregarVenta = new javax.swing.JButton();
        GuardarVenta = new javax.swing.JButton();
        CancelarVenta = new javax.swing.JButton();
        SalirVenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FACTURA DE COMPRA");
        setMinimumSize(new java.awt.Dimension(702, 520));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jLabel1.setText("Codigo Fact");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 80, 30);

        txtcodfact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodfactActionPerformed(evt);
            }
        });
        jPanel1.add(txtcodfact);
        txtcodfact.setBounds(100, 10, 100, 30);

        jLabel2.setText("Fecha");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(590, 20, 40, 14);
        jPanel1.add(txtfecha);
        txtfecha.setBounds(640, 10, 90, 30);

        jLabel3.setText("Proveedor");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 60, 80, 30);

        comboProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboProveedor);
        comboProveedor.setBounds(70, 60, 160, 30);

        jLabel9.setText("Tipo Fact");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(550, 60, 80, 30);

        ComboTipoFactura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(ComboTipoFactura);
        ComboTipoFactura.setBounds(620, 60, 140, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 780, 120);

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setLayout(null);

        jLabel6.setText("TOTAL");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(350, 250, 60, 20);

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel2.add(txtTotal);
        txtTotal.setBounds(430, 240, 100, 30);

        btnagregar_detalle.setText("Agregar Detalle");
        btnagregar_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregar_detalleActionPerformed(evt);
            }
        });
        jPanel2.add(btnagregar_detalle);
        btnagregar_detalle.setBounds(540, 80, 130, 50);

        btneliminar_detalle.setText("Eliminar Detalle");
        jPanel2.add(btneliminar_detalle);
        btneliminar_detalle.setBounds(540, 140, 130, 50);

        tablaventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nro", "Descripción", "Cantidad", "Precio U.", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tablaventas);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 520, 162);

        jLabel4.setText("Insumos");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 30, 70, 14);

        cboInsumos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cboInsumos);
        cboInsumos.setBounds(70, 20, 290, 30);

        jLabel5.setText("Cantidad");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(380, 20, 70, 20);
        jPanel2.add(txtcantidad);
        txtcantidad.setBounds(440, 10, 100, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 130, 770, 280);

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));
        jPanel3.setLayout(null);

        AgregarVenta.setText("Agregar");
        jPanel3.add(AgregarVenta);
        AgregarVenta.setBounds(30, 10, 110, 50);

        GuardarVenta.setText("Guardar");
        GuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarVentaActionPerformed(evt);
            }
        });
        jPanel3.add(GuardarVenta);
        GuardarVenta.setBounds(170, 10, 110, 50);

        CancelarVenta.setText("Cancelar");
        CancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarVentaActionPerformed(evt);
            }
        });
        jPanel3.add(CancelarVenta);
        CancelarVenta.setBounds(310, 10, 110, 50);

        SalirVenta.setText("Salir");
        SalirVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirVentaActionPerformed(evt);
            }
        });
        jPanel3.add(SalirVenta);
        SalirVenta.setBounds(450, 10, 110, 50);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 420, 770, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarVentaActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CancelarVentaActionPerformed

    private void SalirVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirVentaActionPerformed
         int r=JOptionPane.showConfirmDialog(null,"Esta Seguro?");if(r==0){dispose();}
    }//GEN-LAST:event_SalirVentaActionPerformed

    private void GuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GuardarVentaActionPerformed

    private void btnagregar_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregar_detalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregar_detalleActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtcodfactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodfactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodfactActionPerformed

    
   // * @param args the command line arguments
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CFacturaCompra(new VFacturaCompra()).iniciar();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton AgregarVenta;
    public javax.swing.JButton CancelarVenta;
    public javax.swing.JComboBox ComboTipoFactura;
    public javax.swing.JButton GuardarVenta;
    public javax.swing.JButton SalirVenta;
    public javax.swing.JButton btnagregar_detalle;
    public javax.swing.JButton btneliminar_detalle;
    public javax.swing.JComboBox cboInsumos;
    public javax.swing.JComboBox comboProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaventas;
    public javax.swing.JTextField txtTotal;
    public javax.swing.JTextField txtcantidad;
    public javax.swing.JTextField txtcodfact;
    public javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables

}
