create schema javaseweb;

use javaseweb;

--Script--
CREATE TABLE productos (
	id int auto_increment not null,
    precio decimal not null,
    descripcion varchar(255) not null,
    constraint pk_productos primary key (id)
);