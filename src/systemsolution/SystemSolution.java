/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package systemsolution;

import Controlador.CAcceso;
import Controlador.CBarrio;
import Controlador.CCaja;
import Controlador.CCargos;
import Controlador.CCategoria;
import Controlador.CCiudad;
import Controlador.CCliente;
import Controlador.CColor;

import Controlador.CEntidadesEmisoras;
import Controlador.CEquipo;
import Controlador.CFacturaCompra;

import Controlador.CFuncionarios1;
import Controlador.CInsumo;
import Controlador.CMarca;
import Controlador.CMedida;
import Controlador.CModelo;
import Controlador.CMotivoAjuste;
import Controlador.CNacionalidad;
import Controlador.CPermiso;
import Controlador.CProveedor;
import Controlador.CRecepcion;
import Controlador.CTarjeta;
import Controlador.CTipoAjuste;
import Controlador.CTipoEquipo;
import Controlador.CTipoFactura;
import Controlador.CTipoServicio;
import Controlador.CTipoTarjeta;
import Controlador.CUsuario;
import Controlador.CValorMoneda;
import Controlador.CBuscarProductos;
import Vista.VAcceso;
import Vista.VBarrio;
import Vista.VCaja;
import Vista.VCargos;
import Vista.VCategoria;
import Vista.VCiudad;
import Vista.VCliente;
import Vista.VColor;

import Vista.VEntidadesEmisoras;
import Vista.VEquipo;
import Vista.VFacturaCompra;

import Vista.VFuncionario1;
import Vista.VInsumo;
import Vista.VMarca;
import Vista.VMedida;
import Vista.VModelo;
import Vista.VMotivoAjuste;
import Vista.VNacionalidad;
import Vista.VPermiso;
import Vista.VProveedor;
import Vista.VRecepcion;
import Vista.VTarjeta;
import Vista.VTipoAjuste;
import Vista.VTipoEquipo;
import Vista.VTipoFactura;
import Vista.VTipoServicio;
import Vista.VTipotarjeta;
import Vista.VUsuarios;
import Vista.VValorMoneda;
import Vista.VBuscarProductos;



/**
 *
 * @author MIPC
 */
public class SystemSolution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // **new CCargos(new VCargos()).iniciar();
      //*** new CCategoria( new VCategoria()).iniciar();
         //**new CNacionalidad(new VNacionalidad()).iniciar();
        //**new CColor(new VColor()).iniciar();
        // **new CCiudad(new VCiudad()).iniciar();
         // **new CMedida(new VMedida()).iniciar();
         //**new CValorMoneda(new VValorMoneda()).iniciar();
        //** new CTipoServicio(new VTipoServicio()).iniciar();
    // **new CCliente(new VCliente()) . iniciar ();
       //** new CMarca(new VMarca()) . iniciar ();   
        
        //**new CBarrio(new VBarrio()).iniciar ();
       //** new CProveedor(new VProveedor()).iniciar ();
         //**new CEntidadesEmisoras(new VEntidadesEmisoras()).iniciar ();
       //**new CTipoTarjeta(new VTipotarjeta()).iniciar ();
        //**new CTipoEquipo(new VTipoEquipo()).iniciar ();
       //**new CModelo(new VModelo()).iniciar ();
              
      // **new CTarjeta(new VTarjeta()).iniciar (); 
      
      //** new CInsumo(new VInsumo()).iniciar (); 
         //** CEquipo(new VEquipo()).iniciar ();
     //**new CFuncionarios1(new VFuncionario1()) . iniciar ();
   // ** new  CPermiso(new VPermiso()).iniciar ();
      
       //** new  CUsuario(new VUsuarios()).iniciar ();
       //** new  CCaja(new VCaja()).iniciar ();
      //** new CMotivoAjuste(new VMotivoAjuste()).iniciar ();
       //**new CTipoAjuste(new VTipoAjuste()).iniciar ();
       
        //**new CRecepcion(new VRecepcion()).iniciar ();
     //new CBuscarProductos(new VBuscarProductos()).iniciar ();
        
     new  CAcceso(new VAcceso()).iniciar ();
     
      // new CTipoFactura(new VTipoFactura()).iniciar ();
     // new CFacturaCompra(new VFacturaCompra()).iniciar();   
        // TODO code application logic here
    }
    
}
