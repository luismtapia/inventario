use inventario

select * from productos
select * from banco
select * from clientes
select * from ventas
select * from venta_es_de_producto

select sum(precio_compra) total from productos;		--total invertido
select sum(total_invertido) total from productos
select sum(ganancia_esperada) total from productos

select sum(precio_venta) from productos;		--nuevo total

select count(existencia) from productos;		--total de productos

select sum(existencia) from productos;		--total de articulos

select * from productos where codigo like '750105%'

select isnull(max(no_ticket),0) + 1 as folio from ventas

select MAX(existencia) as mayor from productos

select MAX(id_cliente) as ultimo from clientes
select * from banco

update banco set efectivo = '0';
update productos set existencia = '99' where codigo = '7501003301895'
update productos set existencia = '99' where nombre = 'café'
update productos set existencia = '99' where nombre = 'agua nestle'



select * from ventas v inner join venta_es_de_producto vp on v.no_ticket=vp.no_ticket inner join productos p on vp.codigo=p.codigo where nombre = 'ajo'

select * from ventas v inner join clientes c on v.id_cliente = c.id_cliente inner join venta_es_de_producto vp on vp.no_ticket=v.no_ticket inner join productos p on p.codigo=vp.codigo where nombre_cliente = 'anónimo'

--muestra todas las ventas
select * from ventas v inner join clientes c on v.id_cliente = c.id_cliente inner join venta_es_de_producto vp on vp.no_ticket=v.no_ticket inner join productos p on p.codigo=vp.codigo;

