create database inventario;
use inventario;

create table productos(codigo varchar(15) not null,
			nombre varchar(30),
			descripcion varchar (100),
			precio_compra money,
			precio_venta money,
			existencia int,
			total_invertido money,
			ganancia_esperada money,
			constraint PRODUCTOSPK primary key (codigo));

create table clientes(id_cliente int identity (1,1),
					nombre_cliente varchar (50) not null,
					constraint CLIENTESPK primary key (id_cliente));

create table ventas(no_ticket int identity (1,1),
					fecha_venta date,--datetime
					id_cliente int,
					total_a_pagar money,
                    constraint VENTASPK primary key (no_ticket),
					constraint VENTASFK1 foreign key (id_cliente) references clientes(id_cliente));

create table venta_es_de_producto(no_ticket int,
								codigo varchar(15),
								cantidad int,
								precio_venta money,
								--subtotal money,
								constraint TICKETSPK primary key (no_ticket,codigo));

create table banco(total_invertido money,
					utilidades_retenidas money,
					activo_fijo money,
					efectivo money)

--****************************arriba ya esta aplicado pequeñas modificaciones a considerar

create table apartados(codigo varchar(15),
					nombre_cliente varchar (50),
					fecha_apartado date,
					fecha_liquidacion date,
					precio money,
					importe money,
					constraint APARTADOSPK primary key (codigo,nombre_cliente),
					constraint APARTADOSFK1 foreign key (codigo) references productos (codigo),
					constraint APARTADOSFK2 foreign key (nombre_cliente) references clientes (nombre_cliente));




create table activos (numero int identity(1,1),
					  nombre varchar(30),
					  descripcion varchar(200),
					  costo_compra money,
					  constraint ACTIVOSPK primary key (numero,nombre));




drop table venta_es_de_producto
drop table ventas
drop table clientes
drop table productos