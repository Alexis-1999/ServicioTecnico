/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.1.49-community : Database - solutionsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`solutionsystem` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `solutionsystem`;

/*Table structure for table `ajusteexistencia` */

DROP TABLE IF EXISTS `ajusteexistencia`;

CREATE TABLE `ajusteexistencia` (
  `idAjusteExistencia` int(11) NOT NULL AUTO_INCREMENT,
  `Cantidad` int(11) NOT NULL,
  `FechaAjuste` date NOT NULL,
  `IdInsumos` int(11) NOT NULL,
  `IdMotivoAjuste` int(11) NOT NULL,
  `idTipoAjuste` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idAjusteExistencia`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `ajusteexistencia` */

/*Table structure for table `apertuaradecajas` */

DROP TABLE IF EXISTS `apertuaradecajas`;

CREATE TABLE `apertuaradecajas` (
  `idApertuaradeCajas` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `hora` time NOT NULL,
  `MontoApertura` float NOT NULL,
  `NumFactura` int(11) NOT NULL,
  `ApertuaradeCajascol` varchar(45) NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  `idCajas` int(11) NOT NULL,
  PRIMARY KEY (`idApertuaradeCajas`),
  UNIQUE KEY `idApertuaradeCajas_UNIQUE` (`idApertuaradeCajas`),
  KEY `fk_Usuarios8` (`idUsuarios`),
  KEY `fk_Cajas1` (`idCajas`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `apertuaradecajas` */

/*Table structure for table `aperturascierres` */

DROP TABLE IF EXISTS `aperturascierres`;

CREATE TABLE `aperturascierres` (
  `idApertuaradeCajas` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `NumFactura` int(11) NOT NULL,
  `hora` time NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  PRIMARY KEY (`idApertuaradeCajas`),
  KEY `fk_ApertuaradeCajas1` (`idApertuaradeCajas`),
  KEY `fk_Usuarios11` (`idUsuarios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `aperturascierres` */

/*Table structure for table `arqueodecaja` */

DROP TABLE IF EXISTS `arqueodecaja`;

CREATE TABLE `arqueodecaja` (
  `idArqueodeCaja` int(11) NOT NULL AUTO_INCREMENT,
  `MontoGS` float NOT NULL,
  `MontoRS` float NOT NULL,
  `MontoPS` float NOT NULL,
  `Monto$$` float NOT NULL,
  `MontoCheque` float NOT NULL,
  `MontoTarjetaCredito` float NOT NULL,
  `MontoTarjetaDebito` float NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  `idApertuaradeCajas` int(11) NOT NULL,
  PRIMARY KEY (`idArqueodeCaja`),
  UNIQUE KEY `idArqueodeCaja_UNIQUE` (`idArqueodeCaja`),
  KEY `fk_Usuarios9` (`idUsuarios`),
  KEY `fk_ApertuaradeCajas2` (`idApertuaradeCajas`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `arqueodecaja` */

/*Table structure for table `barrios` */

DROP TABLE IF EXISTS `barrios`;

CREATE TABLE `barrios` (
  `idBarrios` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `idCiudades` int(11) NOT NULL,
  PRIMARY KEY (`idBarrios`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `barrios` */

insert  into `barrios`(`idBarrios`,`Descripcion`,`idCiudades`) values (1,'San Francisco',1),(2,'San Antonio',2),(3,'Itacuruby',1),(4,'Itacurubi',4),(5,'Area3',4),(6,'San blas',6),(7,'fbsdjfbdsjfb',5),(8,'jjjjjjjjjjjjjjjjjjjjjjj',7),(9,'Ciudad Nueva',4),(10,'Don Bosco',4),(11,'Vista  Alegre',9),(12,'Area6',7);

/*Table structure for table `cajas` */

DROP TABLE IF EXISTS `cajas`;

CREATE TABLE `cajas` (
  `idCajas` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(20) NOT NULL,
  PRIMARY KEY (`idCajas`),
  UNIQUE KEY `idCajas_UNIQUE` (`idCajas`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `cajas` */

insert  into `cajas`(`idCajas`,`Descripcion`) values (1,'caja 1');

/*Table structure for table `cargos` */

DROP TABLE IF EXISTS `cargos`;

CREATE TABLE `cargos` (
  `idCargos` int(11) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCargos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `cargos` */

insert  into `cargos`(`idCargos`,`Descripcion`) values (3,'Vendedor'),(2,'recepcionistas'),(4,'secretario');

/*Table structure for table `categorias` */

DROP TABLE IF EXISTS `categorias`;

CREATE TABLE `categorias` (
  `idCategorias` int(11) NOT NULL,
  `Categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategorias`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `categorias` */

insert  into `categorias`(`idCategorias`,`Categoria`) values (1,'Notbook'),(2,'Servicios'),(3,'Electronicos');

/*Table structure for table `cheques` */

DROP TABLE IF EXISTS `cheques`;

CREATE TABLE `cheques` (
  `idCheque` int(11) NOT NULL AUTO_INCREMENT,
  `NumerosCheques` varchar(45) NOT NULL,
  `Monto` float NOT NULL,
  `Fecha_Emision` date NOT NULL,
  `Fecha_Cobro` date NOT NULL,
  `idEntidades_Emisoras` int(11) NOT NULL,
  `idCllientes` int(11) NOT NULL,
  PRIMARY KEY (`idCheque`),
  UNIQUE KEY `idCheque_UNIQUE` (`idCheque`),
  KEY `fk_Entidades_Emisoras2` (`idEntidades_Emisoras`),
  KEY `fk_Cllientes2` (`idCllientes`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `cheques` */

/*Table structure for table `ciudades` */

DROP TABLE IF EXISTS `ciudades`;

CREATE TABLE `ciudades` (
  `idCiudades` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idCiudades`),
  UNIQUE KEY `idCiudades_UNIQUE` (`idCiudades`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `ciudades` */

insert  into `ciudades`(`idCiudades`,`Nombre`) values (1,'concepcion'),(2,'Asuncion'),(3,'Luque'),(4,'cde'),(5,'fernando'),(7,'Hernandarias'),(8,'San Lorenzo');

/*Table structure for table `clientes` */

DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (
  `idClientes` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `CI` varchar(45) NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `idBarrios` int(11) NOT NULL,
  `idNacionalidad` int(11) NOT NULL,
  PRIMARY KEY (`idClientes`),
  UNIQUE KEY `idCllientes_UNIQUE` (`idClientes`),
  UNIQUE KEY `CI_UNIQUE` (`CI`),
  KEY `fk_Barrios1` (`idBarrios`),
  KEY `fk_Nacionalidades1` (`idNacionalidad`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `clientes` */

insert  into `clientes`(`idClientes`,`Nombre`,`Apellido`,`CI`,`Direccion`,`Telefono`,`Email`,`idBarrios`,`idNacionalidad`) values (1,'Aurelia','Gayozo','4405800','14 de mayo','0973199953','aubegave@hotmail.com',2,1),(2,'Carlos','Espinola','2563255','Av Pioneros del Este 2301','061584652','Crxl@gmail.com',2,2),(3,'Fernando','Gimenez','58588899','14 de mayo y 13 Proyectadas','589454','Faengimez@gmail.com',4,4),(4,'Priscila','Lopez','6589586','Las Ortencias 478','063120735','Pr75@gmail',5,2),(5,'Luiza ','Gonzalez','255526655','av Itaipu','45895652',' Liui@hotmail.com',11,3),(8,'Lucas','Peralta','5895658','Calle Ultima','095485895','Lucas2gmail.com',12,1),(7,'Jose','Zelaya','4582458','Av Julio Roquelme','0973142735','chrbs@gmail.com',9,1);

/*Table structure for table `colores` */

DROP TABLE IF EXISTS `colores`;

CREATE TABLE `colores` (
  `idColores` int(11) NOT NULL AUTO_INCREMENT,
  `Color` varchar(45) NOT NULL,
  PRIMARY KEY (`idColores`),
  UNIQUE KEY `idColores_UNIQUE` (`idColores`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `colores` */

insert  into `colores`(`idColores`,`Color`) values (1,'rojo'),(2,'Amarillo'),(3,'azul'),(4,'Verde'),(5,'Lila'),(6,'Rosado'),(7,'rosa');

/*Table structure for table `comprobantesdetarjetas` */

DROP TABLE IF EXISTS `comprobantesdetarjetas`;

CREATE TABLE `comprobantesdetarjetas` (
  `idComprobantes` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Monto` float NOT NULL,
  `Observacion` varchar(45) NOT NULL,
  `idTarjetas` int(11) NOT NULL,
  PRIMARY KEY (`idComprobantes`),
  KEY `fk_Tarjetas1` (`idTarjetas`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `comprobantesdetarjetas` */

/*Table structure for table `consumos` */

DROP TABLE IF EXISTS `consumos`;

CREATE TABLE `consumos` (
  `idConsumos` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  `IdtiposServicios` int(11) DEFAULT NULL,
  `idFuncionarios` int(11) DEFAULT NULL,
  PRIMARY KEY (`idConsumos`),
  UNIQUE KEY `idConsumos_UNIQUE` (`idConsumos`),
  KEY `fk_Usuarios4` (`idUsuarios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `consumos` */

/*Table structure for table `cuentascobrar` */

DROP TABLE IF EXISTS `cuentascobrar`;

CREATE TABLE `cuentascobrar` (
  `idCuentasaCobrar` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Monto` float NOT NULL,
  `Saldo` float NOT NULL,
  `Estado` varchar(20) NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  `idServicos` int(11) NOT NULL,
  `idTimbrados` int(11) NOT NULL,
  PRIMARY KEY (`idCuentasaCobrar`),
  KEY `fk_Usuarios16` (`idUsuarios`),
  KEY `fk_FactServicos1` (`idServicos`),
  KEY `fk_Timbrados1` (`idTimbrados`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `cuentascobrar` */

/*Table structure for table `cuentaspagar` */

DROP TABLE IF EXISTS `cuentaspagar`;

CREATE TABLE `cuentaspagar` (
  `idCuentasaPagar` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `Monto` float NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  `idFacturaCompras` int(11) NOT NULL,
  PRIMARY KEY (`idCuentasaPagar`),
  UNIQUE KEY `idCuentasaPagar_UNIQUE` (`idCuentasaPagar`),
  KEY `fk_Usuarios12` (`idUsuarios`),
  KEY `fk_FacturaCompras3` (`idFacturaCompras`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `cuentaspagar` */

/*Table structure for table `detalle_ventas` */

DROP TABLE IF EXISTS `detalle_ventas`;

CREATE TABLE `detalle_ventas` (
  `NumFactura` int(11) NOT NULL,
  `IdtiposServicios` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_venta` float DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  PRIMARY KEY (`NumFactura`,`IdtiposServicios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detalle_ventas` */

insert  into `detalle_ventas`(`NumFactura`,`IdtiposServicios`,`cantidad`,`precio_venta`,`subtotal`) values (1,2,6,5000,NULL),(2,1,2,2000,NULL),(1,1,2,10,20),(3,2,3,8000,24000),(4,2,6,8000,48000),(4,5,1,100000,100000),(5,2,2,8000,16000),(6,1,1,45000,45000),(6,2,1,50000,50000),(7,2,1,50000,50000),(7,3,2,100000,200000),(8,4,2,100000,200000),(9,3,10,100000,1e+006),(10,2,1,50000,50000),(11,3,1,100000,100000),(11,4,2,100000,200000),(12,1,1,45000,45000),(12,3,2,100000,200000),(13,2,1,50000,50000),(13,3,1,100000,100000),(14,4,5,100000,500000),(15,3,2,100000,200000),(16,2,1,50000,50000),(16,3,1,100000,100000),(17,1,2,45000,90000),(21,1,1,25000,NULL),(22,1,1,25000,NULL),(23,5,1,100000,NULL),(23,2,2,25000,NULL),(24,1,2,25000,NULL),(25,1,1,25000,NULL),(26,1,1,25000,NULL),(27,1,1,25000,NULL),(28,3,1,250000,NULL),(29,3,2,250000,NULL),(30,3,2,250000,NULL),(30,5,1,100000,NULL),(31,5,2,100000,NULL),(32,5,1,100000,NULL),(33,1,2,25000,NULL),(34,1,1,25000,NULL),(34,4,1,180000,NULL),(35,6,3,250000,NULL),(35,2,1,25000,NULL),(36,1,1,25000,NULL),(36,5,2,100000,NULL),(37,3,3,250000,NULL),(38,6,1,250000,NULL),(39,2,2,25000,NULL),(39,5,2,100000,NULL);

/*Table structure for table `detalleconsumo` */

DROP TABLE IF EXISTS `detalleconsumo`;

CREATE TABLE `detalleconsumo` (
  `Cantidad` int(11) NOT NULL,
  `idConsumos` int(11) NOT NULL,
  `idInsumos` int(11) NOT NULL,
  PRIMARY KEY (`idConsumos`,`idInsumos`),
  KEY `fk_Consumos1` (`idConsumos`),
  KEY `fk_Insumos2` (`idInsumos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detalleconsumo` */

/*Table structure for table `detallecreditocompra` */

DROP TABLE IF EXISTS `detallecreditocompra`;

CREATE TABLE `detallecreditocompra` (
  `idNotacreditoCompras` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `idInsumos` int(11) NOT NULL,
  `SubTotal` varchar(20) NOT NULL,
  PRIMARY KEY (`idNotacreditoCompras`),
  KEY `fk_NotacreditoCompras1` (`cantidad`),
  KEY `fk_Insumos5` (`idInsumos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detallecreditocompra` */

/*Table structure for table `detalledebitocompra` */

DROP TABLE IF EXISTS `detalledebitocompra`;

CREATE TABLE `detalledebitocompra` (
  `idNotaDebitoCompra` int(11) NOT NULL,
  `Cantidad` varchar(20) NOT NULL,
  `idInsumos` int(11) NOT NULL,
  `subtotal` varchar(20) NOT NULL,
  PRIMARY KEY (`idNotaDebitoCompra`),
  KEY `fk_NotaDebitoCompra1` (`idInsumos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detalledebitocompra` */

/*Table structure for table `detallefacturascompra` */

DROP TABLE IF EXISTS `detallefacturascompra`;

CREATE TABLE `detallefacturascompra` (
  `FacturaCompras` int(11) NOT NULL,
  `cantidad` varchar(20) DEFAULT NULL,
  `precioCompra` float DEFAULT NULL,
  `Subtotal` varchar(20) DEFAULT NULL,
  `idInsumos` int(11) NOT NULL,
  PRIMARY KEY (`FacturaCompras`),
  KEY `fk_FacturaCompras2` (`FacturaCompras`),
  KEY `fk_Insumos4` (`idInsumos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detallefacturascompra` */

insert  into `detallefacturascompra`(`FacturaCompras`,`cantidad`,`precioCompra`,`Subtotal`,`idInsumos`) values (1,'1',22,'10',220),(2,'2',6,'6000',36000),(3,'2',2,'6000',12000),(4,'5',2,'80000',160000),(5,'4',1,'130000',130000),(6,'6',1,'200000',200000),(7,'2',2,'25000',50000),(8,'2',8,'25000',200000);

/*Table structure for table `detalleordencompra` */

DROP TABLE IF EXISTS `detalleordencompra`;

CREATE TABLE `detalleordencompra` (
  `idDetalleOrden` int(11) NOT NULL AUTO_INCREMENT,
  `Cantidad` int(11) NOT NULL,
  `idOrdenCompras` int(11) NOT NULL,
  PRIMARY KEY (`idDetalleOrden`),
  UNIQUE KEY `idDetalleOrden_UNIQUE` (`idDetalleOrden`),
  KEY `fk_OrdenCompras1` (`idOrdenCompras`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detalleordencompra` */

/*Table structure for table `detalleordenservicios` */

DROP TABLE IF EXISTS `detalleordenservicios`;

CREATE TABLE `detalleordenservicios` (
  `idOrdenesDetrabajos` int(11) NOT NULL,
  `IdtiposServicios` int(11) NOT NULL,
  PRIMARY KEY (`idOrdenesDetrabajos`,`IdtiposServicios`),
  KEY `fk_OrdenesDetrabajos2` (`idOrdenesDetrabajos`),
  KEY `fk_TiposServicios2` (`IdtiposServicios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detalleordenservicios` */

/*Table structure for table `detallepedidocompra` */

DROP TABLE IF EXISTS `detallepedidocompra`;

CREATE TABLE `detallepedidocompra` (
  `Cantidad` int(11) NOT NULL,
  `idPedidoCompras` int(11) NOT NULL,
  `idInsumos` int(11) NOT NULL,
  KEY `fk_PedidoCompras1` (`idPedidoCompras`),
  KEY `fk_Insumos3` (`idInsumos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detallepedidocompra` */

/*Table structure for table `detallepresupuesto` */

DROP TABLE IF EXISTS `detallepresupuesto`;

CREATE TABLE `detallepresupuesto` (
  `Presupuestos` int(11) NOT NULL,
  `IdtiposServicios` int(11) NOT NULL,
  `Cantidad` varchar(45) NOT NULL,
  PRIMARY KEY (`Presupuestos`),
  KEY `fk_Presupuestos1` (`Presupuestos`),
  KEY `fk_TiposServicios4` (`IdtiposServicios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `detallepresupuesto` */

insert  into `detallepresupuesto`(`Presupuestos`,`IdtiposServicios`,`Cantidad`) values (1,2,'1');

/*Table structure for table `entidadesemisoras` */

DROP TABLE IF EXISTS `entidadesemisoras`;

CREATE TABLE `entidadesemisoras` (
  `idEntidadesEmisoras` int(11) NOT NULL AUTO_INCREMENT,
  `RazonSocial` varchar(45) NOT NULL,
  `Ruc` varchar(45) NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`idEntidadesEmisoras`),
  UNIQUE KEY `Ruc_UNIQUE` (`Ruc`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `entidadesemisoras` */

insert  into `entidadesemisoras`(`idEntidadesEmisoras`,`RazonSocial`,`Ruc`,`Direccion`,`Telefono`,`Email`) values (1,'Banco Itau','5854445','Gran via','5555555','jdbsfhsdkfjn');

/*Table structure for table `equipos` */

DROP TABLE IF EXISTS `equipos`;

CREATE TABLE `equipos` (
  `idEquipos` int(11) NOT NULL,
  `idMarcas` int(11) NOT NULL,
  `idTiposEquipos` int(11) NOT NULL,
  `idMedidas` int(11) NOT NULL,
  `idColores` int(11) NOT NULL,
  `idModelos` int(11) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEquipos`),
  KEY `fk_Marcas3` (`idMarcas`),
  KEY `fk_TiposEquipos2` (`idTiposEquipos`),
  KEY `fk_Medidas2` (`idMedidas`),
  KEY `fk_Colores2` (`idColores`),
  KEY `fk_Modelos3` (`idModelos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `equipos` */

insert  into `equipos`(`idEquipos`,`idMarcas`,`idTiposEquipos`,`idMedidas`,`idColores`,`idModelos`,`Descripcion`) values (1,22222,1,1,1,1,'1'),(2,5,1,1,1,1,'1'),(3,5,1,1,1,1,'1'),(4,6,1,1,1,1,'1'),(5,5,1,1,1,1,'kjhghj'),(6,8,1,1,1,1,'1'),(7,1,1,1,1,1,'mochila'),(8,1,1,1,1,1,'wwww');

/*Table structure for table `establecimientos` */

DROP TABLE IF EXISTS `establecimientos`;

CREATE TABLE `establecimientos` (
  `idEstablecimientos` int(11) NOT NULL AUTO_INCREMENT,
  `RazonSocial` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Ruc` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `PaguinaWeb` varchar(45) NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `idBarrios` int(11) NOT NULL,
  PRIMARY KEY (`idEstablecimientos`),
  UNIQUE KEY `idEstablecimientos_UNIQUE` (`idEstablecimientos`),
  UNIQUE KEY `Ruc_UNIQUE` (`Ruc`),
  KEY `fk_Barrios3` (`idBarrios`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `establecimientos` */

insert  into `establecimientos`(`idEstablecimientos`,`RazonSocial`,`Telefono`,`Ruc`,`Email`,`PaguinaWeb`,`Direccion`,`idBarrios`) values (1,'JZAsistencia','061876564','80023123-3','zl@gmail.com','www.jz.com.py','Av. Julio Riquelme',2);

/*Table structure for table `factservicios` */

DROP TABLE IF EXISTS `factservicios`;

CREATE TABLE `factservicios` (
  `NumFactura` int(11) NOT NULL,
  `Fecha` varchar(45) NOT NULL,
  `idFuncionarios` int(11) DEFAULT NULL,
  `usuarios` int(11) DEFAULT NULL,
  `IdOrdenServicios` int(11) DEFAULT NULL,
  `idClientes` int(11) NOT NULL,
  `idTipofactura` int(11) DEFAULT NULL,
  `idTimbrados` int(11) NOT NULL,
  `Total` double DEFAULT NULL,
  PRIMARY KEY (`NumFactura`),
  UNIQUE KEY `NumFactuta_UNIQUE` (`NumFactura`),
  KEY `fk_Funcionarios6` (`idFuncionarios`),
  KEY `fk_Usuarios10` (`usuarios`),
  KEY `fk_OrdenesDetrabajos1` (`IdOrdenServicios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `factservicios` */

insert  into `factservicios`(`NumFactura`,`Fecha`,`idFuncionarios`,`usuarios`,`IdOrdenServicios`,`idClientes`,`idTipofactura`,`idTimbrados`,`Total`) values (1,'2016-01-21',3,NULL,NULL,1,1,1,NULL),(2,'2016-01-22',3,NULL,NULL,1,1,1,NULL),(3,'2016-01-27',3,NULL,NULL,1,1,1,NULL),(4,'2016-01-27',3,NULL,NULL,1,1,1,NULL),(5,'2016-01-27',3,NULL,NULL,1,1,1,NULL),(6,'2016-01-27',3,NULL,NULL,1,1,1,NULL),(7,'2016-01-27',3,NULL,NULL,1,1,1,NULL),(8,'2016-04-12',3,NULL,NULL,1,1,1,NULL),(9,'2016-04-12',3,NULL,NULL,1,1,1,NULL),(10,'2016-04-15',5,NULL,NULL,5,1,1,NULL),(11,'2016-04-15',3,NULL,NULL,1,1,1,NULL),(12,'2016-04-16',3,NULL,NULL,5,1,1,NULL),(13,'2016-04-16',3,NULL,NULL,1,1,1,NULL),(14,'2016-04-16',2,NULL,NULL,5,1,1,NULL),(15,'2016-04-16',7,NULL,NULL,5,1,1,NULL),(16,'2016-04-16',2,NULL,NULL,2,1,1,NULL),(17,'2016-04-16',3,NULL,NULL,1,1,1,NULL),(18,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(19,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(20,'2016-04-17',NULL,1,NULL,3,NULL,20,NULL),(21,'2016-04-17',NULL,1,NULL,5,NULL,20,NULL),(22,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(23,'2016-04-17',NULL,1,NULL,5,NULL,20,NULL),(24,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(25,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(26,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(27,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(28,'2016-04-17',NULL,1,NULL,5,NULL,20,NULL),(29,'2016-04-17',NULL,1,NULL,6,NULL,20,NULL),(30,'2016-04-17',NULL,1,NULL,5,NULL,20,NULL),(31,'2016-04-17',NULL,1,NULL,5,NULL,20,NULL),(32,'2016-04-17',NULL,1,NULL,1,NULL,20,NULL),(33,'2016-04-17',NULL,1,NULL,3,NULL,20,NULL),(34,'2016-04-17',NULL,1,NULL,7,NULL,20,NULL),(35,'2016-04-18',NULL,1,NULL,1,NULL,20,NULL),(36,'2016-04-18',NULL,1,NULL,5,NULL,20,NULL),(37,'2016-04-18',NULL,1,NULL,1,NULL,20,NULL),(38,'2016-04-18',NULL,1,NULL,1,NULL,20,NULL),(39,'2016-04-18',NULL,1,NULL,3,NULL,20,NULL);

/*Table structure for table `facturacompras` */

DROP TABLE IF EXISTS `facturacompras`;

CREATE TABLE `facturacompras` (
  `idFacturaCompras` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `idValorCambio` int(11) DEFAULT NULL,
  `idTipofactura` int(11) NOT NULL,
  `idUsuarios` int(11) DEFAULT NULL,
  `idProveedores` int(11) NOT NULL,
  PRIMARY KEY (`idFacturaCompras`),
  KEY `fk_ValorCambio1` (`idValorCambio`),
  KEY `fk_Tipofactura1` (`idTipofactura`),
  KEY `fk_Usuarios6` (`idUsuarios`),
  KEY `fk_Funcionarios4` (`idProveedores`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `facturacompras` */

insert  into `facturacompras`(`idFacturaCompras`,`Fecha`,`idValorCambio`,`idTipofactura`,`idUsuarios`,`idProveedores`) values (1,'2016-01-22',NULL,1,NULL,1),(2,'2016-01-25',NULL,1,NULL,1),(3,'2016-01-27',NULL,1,NULL,1),(4,'2016-04-17',NULL,2,NULL,1),(5,'2016-04-18',NULL,2,NULL,1),(6,'2016-04-18',NULL,1,NULL,1),(7,'2016-04-18',NULL,1,NULL,1),(8,'2016-04-18',NULL,1,NULL,1);

/*Table structure for table `funcionarios` */

DROP TABLE IF EXISTS `funcionarios`;

CREATE TABLE `funcionarios` (
  `idFuncionarios` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `CI` varchar(45) NOT NULL,
  `fechaNac` date NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Salario` varchar(45) NOT NULL,
  `Sexo` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `idCargos` int(11) NOT NULL,
  `idBarrios` int(11) NOT NULL,
  PRIMARY KEY (`idFuncionarios`,`idBarrios`),
  UNIQUE KEY `CI_UNIQUE` (`CI`),
  KEY `fk_Cargos1` (`Email`),
  KEY `fk_Barrios4` (`idCargos`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `funcionarios` */

insert  into `funcionarios`(`idFuncionarios`,`Nombre`,`Apellido`,`CI`,`fechaNac`,`Direccion`,`Telefono`,`Salario`,`Sexo`,`Email`,`idCargos`,`idBarrios`) values (3,'Maria','Colman','584574','2015-12-12','san blas','64651','15465135153','femenimo','Amknsdklfncsdj',3,2),(2,'Aure','Gayozo','4405800','1985-06-16','14 de mayo','0973199953','2500000','Femenino','aubegave',2,5),(4,'Anibal','Gonzalez','545475','2016-01-10','SAn blas','45865','5000000','masculino','dbasjd',2,1),(5,'julio','lopez','4587','2016-01-19','ahsdgawqhdj','d wjdb','djasdb','d ','d',3,1),(6,'Aurelia','Gayozo','bfhdfgewf','1985-06-16','Rua Jocob Guilich 127','0973199953',' sdcbsdhcgsdbcdsj','femenino','aubegave@hotmail.com',3,1),(7,'jorge','Gonzalez vera','58754','2016-01-24','san blas','855','1200000','masculino','jasdksufsjafdsu',2,1);

/*Table structure for table `insumos` */

DROP TABLE IF EXISTS `insumos`;

CREATE TABLE `insumos` (
  `idInsumos` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `cantidad` varchar(20) NOT NULL,
  `PrecioCompra` varchar(20) NOT NULL,
  `PrecioVenta` varchar(20) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  `idMarcas` int(11) NOT NULL,
  `idCategorias` int(11) NOT NULL,
  `idColores` int(11) NOT NULL,
  `idModelos` int(11) NOT NULL,
  `idMedidas` int(11) NOT NULL,
  PRIMARY KEY (`idInsumos`),
  UNIQUE KEY `idInsumos_UNIQUE` (`idInsumos`),
  KEY `fk_Marcas4` (`idMarcas`),
  KEY `fk_Categorias1` (`idCategorias`),
  KEY `fk_Colores1` (`idColores`),
  KEY `fk_Modelos1` (`idModelos`),
  KEY `fk_Medidas1` (`idMedidas`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `insumos` */

insert  into `insumos`(`idInsumos`,`Descripcion`,`cantidad`,`PrecioCompra`,`PrecioVenta`,`foto`,`idMarcas`,`idCategorias`,`idColores`,`idModelos`,`idMedidas`) values (2,'carga de tintas','10','25000','25000',NULL,1,2,2,1,2),(3,'Display acer 15','25','150000','250000',NULL,1,1,1,1,1),(4,'Cargador toshiba','1','130000','180000',NULL,1,1,1,1,1),(5,'Placa Madre Asus p5kpl','21','80000','100000',NULL,1,1,1,1,1),(6,'Placa Madre Intel b845','16','200000','250000',NULL,2,1,1,1,2);

/*Table structure for table `insumos_proveedores` */

DROP TABLE IF EXISTS `insumos_proveedores`;

CREATE TABLE `insumos_proveedores` (
  `idInsumos` int(11) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  PRIMARY KEY (`idInsumos`,`idProveedor`),
  KEY `fk_Insumos1` (`idInsumos`),
  KEY `fk_Proveedores1` (`idProveedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `insumos_proveedores` */

/*Table structure for table `marcas` */

DROP TABLE IF EXISTS `marcas`;

CREATE TABLE `marcas` (
  `idMarcas` int(11) NOT NULL,
  `Marca` varchar(45) NOT NULL,
  PRIMARY KEY (`idMarcas`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `marcas` */

insert  into `marcas`(`idMarcas`,`Marca`) values (1,'Acer'),(2,'Toshiba'),(3,'Dell'),(4,'Philips'),(5,'Aple');

/*Table structure for table `medidas` */

DROP TABLE IF EXISTS `medidas`;

CREATE TABLE `medidas` (
  `idMedidas` int(11) NOT NULL,
  `Medida` varchar(45) NOT NULL,
  PRIMARY KEY (`idMedidas`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `medidas` */

insert  into `medidas`(`idMedidas`,`Medida`) values (1,'15'),(2,'14'),(3,'17');

/*Table structure for table `modelos` */

DROP TABLE IF EXISTS `modelos`;

CREATE TABLE `modelos` (
  `idModelos` int(11) NOT NULL,
  `Modelo` varchar(45) NOT NULL,
  PRIMARY KEY (`idModelos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `modelos` */

insert  into `modelos`(`idModelos`,`Modelo`) values (1,'Aspire'),(2,'Pavillion'),(3,'One'),(4,'Deskjet'),(5,'P5kpl-Mx');

/*Table structure for table `motivoajuste` */

DROP TABLE IF EXISTS `motivoajuste`;

CREATE TABLE `motivoajuste` (
  `idMotivoAjuste` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idMotivoAjuste`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `motivoajuste` */

/*Table structure for table `nacionalidades` */

DROP TABLE IF EXISTS `nacionalidades`;

CREATE TABLE `nacionalidades` (
  `idNacionalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idNacionalidad`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `nacionalidades` */

insert  into `nacionalidades`(`idNacionalidad`,`Descripcion`) values (1,'paraguaya'),(2,'Argentina'),(3,'Brasilera'),(4,'Peruana');

/*Table structure for table `notacreditocompras` */

DROP TABLE IF EXISTS `notacreditocompras`;

CREATE TABLE `notacreditocompras` (
  `idNotacreditoCompras` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idFacturaCompras` int(11) NOT NULL,
  `idUsuarios` int(11) DEFAULT NULL,
  `idProveedor` int(11) NOT NULL,
  PRIMARY KEY (`idNotacreditoCompras`),
  UNIQUE KEY `idNotacreditoCompras_UNIQUE` (`idNotacreditoCompras`),
  KEY `fk_FacturaCompras4` (`idFacturaCompras`),
  KEY `fk_Usuarios14` (`idUsuarios`),
  KEY `fk_Cllientes3` (`idProveedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `notacreditocompras` */

/*Table structure for table `notadebitocompra` */

DROP TABLE IF EXISTS `notadebitocompra`;

CREATE TABLE `notadebitocompra` (
  `idNotaDebitoCompra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idFacturaCompras` int(11) NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  `idpProveedores` int(11) NOT NULL,
  PRIMARY KEY (`idNotaDebitoCompra`),
  UNIQUE KEY `idNotaDebitoCompra_UNIQUE` (`idNotaDebitoCompra`),
  KEY `fk_FacturaCompras5` (`idFacturaCompras`),
  KEY `fk_Usuarios15` (`idUsuarios`),
  KEY `fk_Cllientes4` (`idpProveedores`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `notadebitocompra` */

/*Table structure for table `ordencompras` */

DROP TABLE IF EXISTS `ordencompras`;

CREATE TABLE `ordencompras` (
  `idOrdenCompras` int(11) NOT NULL,
  `Fecha` varchar(45) DEFAULT NULL,
  `idProveedor` int(11) NOT NULL,
  PRIMARY KEY (`idOrdenCompras`),
  KEY `fk_Proveedores3` (`idProveedor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `ordencompras` */

/*Table structure for table `ordenesdeservicos` */

DROP TABLE IF EXISTS `ordenesdeservicos`;

CREATE TABLE `ordenesdeservicos` (
  `idOrdenesDetrabajos` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idFuncionarios` int(11) NOT NULL,
  `idRecepciones` int(11) NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  PRIMARY KEY (`idOrdenesDetrabajos`),
  UNIQUE KEY `idOrdenesDetrabajos_UNIQUE` (`idOrdenesDetrabajos`),
  KEY `fk_Funcionarios3` (`idFuncionarios`),
  KEY `fk_Recepciones1` (`idRecepciones`),
  KEY `fk_Usuarios3` (`idUsuarios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `ordenesdeservicos` */

/*Table structure for table `ordenespagos` */

DROP TABLE IF EXISTS `ordenespagos`;

CREATE TABLE `ordenespagos` (
  `idOrdenesPagos` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Monto` float DEFAULT NULL,
  `idFacturaCompras` int(11) NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  PRIMARY KEY (`idOrdenesPagos`),
  KEY `fk_FacturaCompras1` (`idFacturaCompras`),
  KEY `fk_Usuarios7` (`idUsuarios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `ordenespagos` */

/*Table structure for table `pedidocompras` */

DROP TABLE IF EXISTS `pedidocompras`;

CREATE TABLE `pedidocompras` (
  `idPedidoCompras` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `idProveedor` int(11) NOT NULL,
  `idUsuarios` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPedidoCompras`),
  UNIQUE KEY `idPedidoCompras_UNIQUE` (`idPedidoCompras`),
  KEY `fk_Proveedores2` (`idProveedor`),
  KEY `fk_Usuarios5` (`idUsuarios`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `pedidocompras` */

/*Table structure for table `permisos` */

DROP TABLE IF EXISTS `permisos`;

CREATE TABLE `permisos` (
  `idPermisos` int(11) NOT NULL,
  `NivelAcceso` varchar(45) NOT NULL,
  PRIMARY KEY (`idPermisos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `permisos` */

insert  into `permisos`(`idPermisos`,`NivelAcceso`) values (1,'Director de RRHH'),(2,'Departamento logistica'),(3,'Principal');

/*Table structure for table `presupuestos` */

DROP TABLE IF EXISTS `presupuestos`;

CREATE TABLE `presupuestos` (
  `idPresupuestos` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Total` varchar(45) NOT NULL,
  `idUsuarios` int(11) DEFAULT NULL,
  `idFuncionarios` int(11) NOT NULL,
  `idRecepciones` int(11) NOT NULL,
  PRIMARY KEY (`idPresupuestos`),
  KEY `fk_Usuarios2` (`idUsuarios`),
  KEY `fk_Funcionarios2` (`idFuncionarios`),
  KEY `fk_Recepciones2` (`idRecepciones`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `presupuestos` */

insert  into `presupuestos`(`idPresupuestos`,`Fecha`,`Total`,`idUsuarios`,`idFuncionarios`,`idRecepciones`) values (1,'2016-01-26','50000',5,3,3);

/*Table structure for table `proveedores` */

DROP TABLE IF EXISTS `proveedores`;

CREATE TABLE `proveedores` (
  `idProveedores` int(11) NOT NULL AUTO_INCREMENT,
  `RazonSocial` varchar(45) NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `Ruc` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `idBarrios` int(11) NOT NULL,
  PRIMARY KEY (`idProveedores`),
  UNIQUE KEY `Ruc_UNIQUE` (`Ruc`),
  UNIQUE KEY `idProveedor_UNIQUE` (`idProveedores`),
  KEY `fk_Barrios2` (`idBarrios`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `proveedores` */

insert  into `proveedores`(`idProveedores`,`RazonSocial`,`Direccion`,`Ruc`,`Telefono`,`Email`,`idBarrios`) values (1,'Casa Gonzalito','14  de mayo y avda san blas','565875','55558485','artur@hotmail.com',1),(2,'casa laura','dnaskjd',' dsakjdn','dasmdnasd','d sajdn',4);

/*Table structure for table `recaudaciones` */

DROP TABLE IF EXISTS `recaudaciones`;

CREATE TABLE `recaudaciones` (
  `idRecaudaciones` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Monto` float NOT NULL,
  `idUsuarios` int(11) NOT NULL,
  `idArqueodeCaja` int(11) NOT NULL,
  PRIMARY KEY (`idRecaudaciones`),
  KEY `fk_Usuarios13` (`idUsuarios`),
  KEY `fk_ArqueodeCaja1` (`idArqueodeCaja`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `recaudaciones` */

/*Table structure for table `recepciones` */

DROP TABLE IF EXISTS `recepciones`;

CREATE TABLE `recepciones` (
  `idRecepciones` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `Fecha` date NOT NULL,
  `idFuncionarios` int(11) NOT NULL,
  `idUsuarios` int(11) DEFAULT NULL,
  `idEquipos` int(11) NOT NULL,
  `idPermisos` int(11) NOT NULL,
  `idTiposEquipos` int(11) NOT NULL,
  `idClientes` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRecepciones`),
  UNIQUE KEY `idRecepciones_UNIQUE` (`idRecepciones`),
  KEY `fk_Funcionarios1` (`idFuncionarios`),
  KEY `fk_Usuarios1` (`idUsuarios`),
  KEY `fk_Equipos1` (`idEquipos`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `recepciones` */

insert  into `recepciones`(`idRecepciones`,`Descripcion`,`Fecha`,`idFuncionarios`,`idUsuarios`,`idEquipos`,`idPermisos`,`idTiposEquipos`,`idClientes`) values (1,'dezcr','2016-01-01',0,1,1,0,0,NULL),(2,'nnn','2016-01-18',0,1,1,0,0,NULL);

/*Table structure for table `tarjetas` */

DROP TABLE IF EXISTS `tarjetas`;

CREATE TABLE `tarjetas` (
  `idTarjetas` int(11) NOT NULL AUTO_INCREMENT,
  `Nro_Tarjeta` varchar(45) NOT NULL,
  `Monto` varchar(45) NOT NULL,
  `Fecha_Emision` varchar(45) NOT NULL,
  `Fecha_Cobro` varchar(45) NOT NULL,
  `idTipoTarjetas` int(11) NOT NULL,
  `idEntidadesEmisoras` int(11) NOT NULL,
  `idClientes` int(11) NOT NULL,
  PRIMARY KEY (`idTarjetas`),
  KEY `fk_TipoTarjetas1` (`idTipoTarjetas`),
  KEY `fk_Entidades_Emisoras1` (`idEntidadesEmisoras`),
  KEY `fk_Cllientes1` (`idClientes`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tarjetas` */

/*Table structure for table `timbrados` */

DROP TABLE IF EXISTS `timbrados`;

CREATE TABLE `timbrados` (
  `idTimbrados` varbinary(20) NOT NULL,
  `Num_Ini` varchar(45) NOT NULL,
  `Num_Final` varchar(45) NOT NULL,
  `Fecha_Validez` varchar(45) NOT NULL,
  `idEstablecimientos` int(11) NOT NULL,
  PRIMARY KEY (`idTimbrados`),
  KEY `fk_Establecimientos1` (`idEstablecimientos`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `timbrados` */

insert  into `timbrados`(`idTimbrados`,`Num_Ini`,`Num_Final`,`Fecha_Validez`,`idEstablecimientos`) values ('155888','1','50','2017-02-20',1);

/*Table structure for table `tipoajuste` */

DROP TABLE IF EXISTS `tipoajuste`;

CREATE TABLE `tipoajuste` (
  `idtipoAjuste` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipoAjuste`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tipoajuste` */

insert  into `tipoajuste`(`idtipoAjuste`,`Descripcion`) values (1,'lllll');

/*Table structure for table `tipofactura` */

DROP TABLE IF EXISTS `tipofactura`;

CREATE TABLE `tipofactura` (
  `idTipofactura` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipofactura`),
  UNIQUE KEY `idTipofactura_UNIQUE` (`idTipofactura`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tipofactura` */

insert  into `tipofactura`(`idTipofactura`,`Descripcion`) values (1,'contado'),(2,'credito');

/*Table structure for table `tiposequipos` */

DROP TABLE IF EXISTS `tiposequipos`;

CREATE TABLE `tiposequipos` (
  `idTiposEquipos` int(11) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idTiposEquipos`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `tiposequipos` */

insert  into `tiposequipos`(`idTiposEquipos`,`Descripcion`) values (1,'note'),(2,'computadora de mesa'),(3,'tablet');

/*Table structure for table `tiposservicios` */

DROP TABLE IF EXISTS `tiposservicios`;

CREATE TABLE `tiposservicios` (
  `IdtiposServicios` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `Precio` double NOT NULL,
  PRIMARY KEY (`IdtiposServicios`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tiposservicios` */

insert  into `tiposservicios`(`IdtiposServicios`,`Descripcion`,`Precio`) values (1,'limpieza',45000),(2,'reparacion',50000),(3,'Instalacion',100000),(4,'recarga de cartucho',100000);

/*Table structure for table `tipotarjetas` */

DROP TABLE IF EXISTS `tipotarjetas`;

CREATE TABLE `tipotarjetas` (
  `idTipoTarjetas` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(20) NOT NULL,
  PRIMARY KEY (`idTipoTarjetas`),
  UNIQUE KEY `idTipoTarjetas_UNIQUE` (`idTipoTarjetas`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tipotarjetas` */

insert  into `tipotarjetas`(`idTipoTarjetas`,`Descripcion`) values (1,'Credito'),(2,'Debito');

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `idUsuarios` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(40) NOT NULL,
  `contrasenha` varchar(10) NOT NULL,
  `idFuncionarios` int(11) NOT NULL,
  `idPermisos` int(11) NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE KEY `contrasenha_UNIQUE` (`contrasenha`),
  KEY `fk_Funcionarios5` (`idFuncionarios`),
  KEY `fk_Permisos1` (`idPermisos`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `usuarios` */

insert  into `usuarios`(`idUsuarios`,`usuario`,`contrasenha`,`idFuncionarios`,`idPermisos`) values (5,'Aure','123456',6,3),(2,'','333',4,1),(3,'','456',3,2),(4,'chiqui','789',5,2),(1,'jose','zelaya',7,1);

/*Table structure for table `valormonedas` */

DROP TABLE IF EXISTS `valormonedas`;

CREATE TABLE `valormonedas` (
  `idValorCambio` int(11) NOT NULL AUTO_INCREMENT,
  `Moneda` varchar(45) NOT NULL,
  `Signo` varchar(45) NOT NULL,
  `CambioActual` double NOT NULL,
  PRIMARY KEY (`idValorCambio`),
  UNIQUE KEY `idValorCambio_UNIQUE` (`idValorCambio`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `valormonedas` */

insert  into `valormonedas`(`idValorCambio`,`Moneda`,`Signo`,`CambioActual`) values (1,'guaranies','GS',1400),(2,'Dolar','$',5500);

/*Table structure for table `ventas` */

DROP TABLE IF EXISTS `ventas`;

CREATE TABLE `ventas` (
  `nro_factura` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `cliente` int(11) DEFAULT NULL,
  `nombreCli` varchar(50) NOT NULL,
  `usuarios` char(20) DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`nro_factura`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `ventas` */

insert  into `ventas`(`nro_factura`,`fecha`,`cliente`,`nombreCli`,`usuarios`,`total`) values (1,'2014-05-17',3,'Giulia','ADM',NULL),(2,'2014-05-17',3,'Giulia','ADM',NULL);

/*Table structure for table `vinsumos` */

DROP TABLE IF EXISTS `vinsumos`;

/*!50001 DROP VIEW IF EXISTS `vinsumos` */;
/*!50001 DROP TABLE IF EXISTS `vinsumos` */;

/*!50001 CREATE TABLE  `vinsumos`(
 `idInsumos` int(11) ,
 `Descripcion` varchar(45) ,
 `cantidad` varchar(20) ,
 `PrecioCompra` varchar(20) ,
 `PrecioVenta` varchar(20) ,
 `foto` varchar(45) ,
 `idMarcas` int(11) ,
 `idCategorias` int(11) ,
 `idColores` int(11) ,
 `idModelos` int(11) ,
 `idMedidas` int(11) ,
 `Marca` varchar(45) ,
 `Categoria` varchar(45) ,
 `Color` varchar(45) ,
 `Modelo` varchar(45) ,
 `Medida` varchar(45) 
)*/;

/*Table structure for table `vtimbrados` */

DROP TABLE IF EXISTS `vtimbrados`;

/*!50001 DROP VIEW IF EXISTS `vtimbrados` */;
/*!50001 DROP TABLE IF EXISTS `vtimbrados` */;

/*!50001 CREATE TABLE  `vtimbrados`(
 `idTimbrados` varbinary(20) ,
 `Num_Ini` varchar(45) ,
 `Num_Final` varchar(45) ,
 `Fecha_Validez` varchar(45) ,
 `idEstablecimientos` int(11) ,
 `RazonSocial` varchar(45) 
)*/;

/*View structure for view vinsumos */

/*!50001 DROP TABLE IF EXISTS `vinsumos` */;
/*!50001 DROP VIEW IF EXISTS `vinsumos` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vinsumos` AS (select `insumos`.`idInsumos` AS `idInsumos`,`insumos`.`Descripcion` AS `Descripcion`,`insumos`.`cantidad` AS `cantidad`,`insumos`.`PrecioCompra` AS `PrecioCompra`,`insumos`.`PrecioVenta` AS `PrecioVenta`,`insumos`.`foto` AS `foto`,`insumos`.`idMarcas` AS `idMarcas`,`insumos`.`idCategorias` AS `idCategorias`,`insumos`.`idColores` AS `idColores`,`insumos`.`idModelos` AS `idModelos`,`insumos`.`idMedidas` AS `idMedidas`,`marcas`.`Marca` AS `Marca`,`categorias`.`Categoria` AS `Categoria`,`colores`.`Color` AS `Color`,`modelos`.`Modelo` AS `Modelo`,`medidas`.`Medida` AS `Medida` from (((((`insumos` join `marcas`) join `categorias`) join `colores`) join `modelos`) join `medidas`) where ((`insumos`.`idMarcas` = `marcas`.`idMarcas`) and (`insumos`.`idCategorias` = `categorias`.`idCategorias`) and (`insumos`.`idColores` = `colores`.`idColores`) and (`insumos`.`idModelos` = `modelos`.`idModelos`) and (`insumos`.`idMedidas` = `medidas`.`idMedidas`))) */;

/*View structure for view vtimbrados */

/*!50001 DROP TABLE IF EXISTS `vtimbrados` */;
/*!50001 DROP VIEW IF EXISTS `vtimbrados` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtimbrados` AS (select `timbrados`.`idTimbrados` AS `idTimbrados`,`timbrados`.`Num_Ini` AS `Num_Ini`,`timbrados`.`Num_Final` AS `Num_Final`,`timbrados`.`Fecha_Validez` AS `Fecha_Validez`,`timbrados`.`idEstablecimientos` AS `idEstablecimientos`,`establecimientos`.`RazonSocial` AS `RazonSocial` from (`timbrados` join `establecimientos`) where (`timbrados`.`idEstablecimientos` = `establecimientos`.`idEstablecimientos`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
