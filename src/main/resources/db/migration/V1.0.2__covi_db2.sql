CREATE TABLE COV01_USER(
	id bigint unsigned not null auto_increment unique,
	email varchar(50),
	
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE COV02_CITIZEN(
	id bigint unsigned not null auto_increment unique,
	name varchar(100) not null,
	age smallint default null,
	gender char not null,
	user_id bigint unsigned default null,
	location_id bigint unsigned default null,
	date_created datetime default current_timestamp not null, 
	
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE COV03_LOCATION(
	id bigint unsigned not null auto_increment unique,
	longitude decimal(23,20) not null,
	latitude decimal(23,20) not null,
	address varchar(100) not null,
	description varchar(500) not null,
	
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE COV04_FAMILY(
	citizen_principal_id bigint unsigned not null,
	citizen_red_id bigint unsigned not null,
	
	PRIMARY KEY (citizen_principal_id, citizen_red_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE COV05_HEALTH_STATUS(
	id smallint,
	status varchar(50),
	
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE COV06_HEALTH_HISTORY(
	id bigint unsigned not null auto_increment unique, 
	citizen_id bigint unsigned not null,
	health_status_id smallint not null,
	date_creation datetime default current_timestamp not null,
	
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE COV07_HISTORY_LOCATION(
	location_id bigint unsigned not null,
	citizen_id bigint unsigned not null,
	date_creation datetime default current_timestamp not null,
	
	PRIMARY KEY (location_id, citizen_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;