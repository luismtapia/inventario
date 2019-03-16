use inventario;

create procedure existe_productos
as
begin
	IF EXISTS (SELECT * FROM sysobjects WHERE type = 'U' AND name = 'productos')
		BEGIN
			PRINT 'Existe'
		END
	ELSE
		BEGIN
			PRINT 'no existe creando'
			create table productos(codigo varchar(15) not null,
								nombre varchar(30),
								descripcion varchar (100),
								precio_compra money,
								precio_venta money,
								existencia int,
								total_invertido money,
								ganancia_esperada money,
								constraint PRODUCTOSPK primary key (codigo));
		END
end


exec existe_productos




drop procedure existe_productos