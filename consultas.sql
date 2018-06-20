use inventario

select * from productos

select sum(precio_compra) total from productos;		--total invertido
select sum(total_invertido) total from productos
select sum(ganancia_esperada) total from productos

select sum(precio_venta) from productos;		--nuevo total

select count(existencia) from productos;		--total de productos

select sum(existencia) from productos;		--total de articulos

select * from productos where codigo like '750105%'

select isnull(max(no_ticket),0) + 1 as folio from ventas

select MAX(existencia) as mayor from productos

update productos set existencia = '25' where codigo = '7501003301895'