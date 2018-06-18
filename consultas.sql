use inventario

select * from productos;

select sum(precio_compra) from productos;		--total invertido

select sum(precio_venta) from productos;		--nuevo total

select count(existencia) from productos;		--total de productos

select sum(existencia) from productos;		--total de articulos