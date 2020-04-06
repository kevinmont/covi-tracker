CREATE TABLE COV01_USER(
	id serial not null unique,
	email varchar(50),
	
	PRIMARY KEY (id)
);


CREATE TABLE COV02_CITIZEN(
	id serial not null unique,
	name varchar(100) not null,
	age smallint default null,
	gender char not null,
	user_id integer default null,
	location_id integer default null,
	date_created timestamp default current_timestamp not null, 
	
	PRIMARY KEY (id)
);

CREATE TABLE COV03_LOCATION(
	id serial not null unique,
	longitude decimal(23,20) not null,
	latitude decimal(23,20) not null,
	address varchar(100) not null,
	description varchar(500) not null,
	
	PRIMARY KEY (id)
);


CREATE TABLE COV04_FAMILY(
	citizen_principal_id integer not null,
	citizen_red_id integer not null,
	
	PRIMARY KEY (citizen_principal_id, citizen_red_id)
);

CREATE TABLE COV05_HEALTH_STATUS(
	id smallint,
	status varchar(50),
	
	PRIMARY KEY (id)
);

CREATE TABLE COV06_HEALTH_HISTORY(
	id serial not null unique, 
	citizen_id integer not null,
	health_status_id smallint not null,
	date_creation timestamp default current_timestamp not null,
	
	PRIMARY KEY (id)
);

CREATE TABLE COV07_HISTORY_LOCATION(
	location_id integer not null,
	citizen_id integer not null,
	date_creation timestamp default current_timestamp not null,
	
	PRIMARY KEY (location_id, citizen_id)
);