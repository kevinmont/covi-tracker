-- add support for geometry
CREATE EXTENSION postgis;

-- drop columns
ALTER TABLE COV03_LOCATION DROP COLUMN longitude;
 
ALTER TABLE COV03_LOCATION DROP COLUMN latitude;

ALTER TABLE COV03_LOCATION DROP COLUMN description;

-- new columns
ALTER TABLE COV03_LOCATION ADD COLUMN coordinates geometry;

ALTER TABLE COV03_LOCATION ADD COLUMN city varchar(100);

ALTER TABLE COV03_LOCATION ADD COLUMN zip_code integer;