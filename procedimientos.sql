use inventario;

create trigger totales
		on productos for insert--,delete,update
	as
	begin
		declare @codigo varchar(15)
		declare @precio_compra money
		declare @precio_venta money
		declare @existencia int
		declare @total_invertido money
		declare @ganancia_esperada money

		declare recorrido cursor for
				select codigo, precio_compra, precio_venta, existencia from productos
		open recorrido
			fetch next from recorrido into @codigo,  @precio_compra, @precio_venta, @existencia
			while @@FETCH_STATUS=0
			begin
				set @total_invertido = @precio_compra*@existencia
				set @ganancia_esperada = @precio_venta-@precio_compra
				set @ganancia_esperada = @ganancia_esperada*@existencia
				print ''+str(@precio_compra)+'    -   '+str(@precio_venta)+'    -   '+str(@existencia)+'    -   '+str(@total_invertido)    --+'    -   '+str(@ganancia_esperada)
				update productos
					set total_invertido = @total_invertido, ganancia_esperada = @ganancia_esperada
						where codigo = @codigo
				fetch next from recorrido into @codigo,  @precio_compra, @precio_venta, @existencia
			end
		close recorrido
		deallocate recorrido
	end

drop trigger totales
















create trigger apartando
							on apartados for insert
as
begin
			--si se inserta un nuevo apartado se tiene que quitar la existencia de productos en la cantidad apartada
end


create procedure totales
	as
		declare @total_invertido money
		set @total_invertido = select suma
	begin






/*
create trigger totales
		on productos for insert,delete,update
	as
		declare @precio_compra money
		--para insert de inserted
		set @precio_compra =  select precio_compra from inserted;
		update
	begin
	*/

create table resumen(total_invertido money,
					ganancia_esperada money,--ya integradios en productos
					activo_fijo money,
					efectivo money)