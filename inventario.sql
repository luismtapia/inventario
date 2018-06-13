use FERNANDA;
use cuentas

-------------------------------------------------------         VENTAS           ---------------------------------------------

/*                            Las siguientes dos vistas son para ver a que departamento se ingresara                        */
create view VistaProductosDepto50 as
	select cve_producto,descripcion,precio_venta from PRODUCTOS
	where cve_depto = '1'
with check option;

drop view VistaProductosDepto40

create view VistaProductosDepto40 as
	select cve_producto,descripcion,precio_venta from PRODUCTOS
	where cve_depto = '2'
with check option;


/*                              Vista que nos muestra los productos y sus respectivas tallas                                */
create view VistaTicket
as select p.cve_producto, p.descripcion, p.precio_venta, t.numero
     from PRODUCTOS p inner join TALLAS_ESDE_PRODUCTO t on t.cve_producto = p.cve_producto
with check option;
























