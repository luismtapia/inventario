create database inventario;

use inventario;

create table productos(codigo varchar(5) not null,
					nombre varchar(30),
					             descripcion varchar (100),
										 	precio_compra money,
					precio_venta money,
											 existencia int,
					             constraint PRODUCTOSPK primary key (codigo));

create table clientes(nombre_cliente varchar (50) not null,
											constraint CLIENTESPK primary key (nombre));

create table apartados(codigo varchar(5),
										   nombre_cliente varchar (50),
										   fecha_apartado date,
                       fecha_liquidacion date,
									     precio money,
									     importe money,
									     constraint APARTADOSPK primary key (codigo,nombre_cliente)
										   constraint APARTADOSFK1 foreign key (codigo) references productos (codigo)
										   constraint APARTADOSFK2 foreign key (nombre_cliente) references clientes (nombre_cliente));

create table venta_es_de_producto(no_ticket int,
																	codigo varchar(5));

create table ventas(codigo varchar (5),
										cantidad int,
										fecha_venta date,
										precio_venta money,
									  total_a_pagar money,
                    constraint VENTASPK primary key (codigo));


create table activos (numero int not null,
		      nombre varchar(30),
		      descripcion varchar(200)
		      );
						



create trigger apartando
							on apartados for insert
as
begin
			--si se inserta un nuevo apartado se tiene que quitar la existencia de productos en la cantidad apartada
end
