ALTER TABLE COV02_CITIZEN
	ADD CONSTRAINT FCOV01_TCOV02_TCOV01 FOREIGN KEY(user_id)
	REFERENCES COV01_USER (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
	
ALTER TABLE COV02_CITIZEN
	ADD CONSTRAINT FCOV02_TCOV02_TCOV03 FOREIGN KEY(location_id)
	REFERENCES COV03_LOCATION (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
	
ALTER TABLE COV04_FAMILY
	ADD CONSTRAINT FCOV03_TCOV04_TCOV02 FOREIGN KEY(citizen_principal)
	REFERENCES COV02_CITIZEN (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
ALTER TABLE COV04_FAMILY
	ADD CONSTRAINT FCOV04_TCOV04_TCOV02 FOREIGN KEY(citizen_red)
	REFERENCES COV02_CITIZEN (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
	
ALTER TABLE COV06_HEALTH_HISTORY
	ADD CONSTRAINT FCOV05_TCOV06_TCOV02 FOREIGN KEY(citizen_id)
	REFERENCES COV02_CITIZEN (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
ALTER TABLE COV06_HEALTH_HISTORY
	ADD CONSTRAINT FCOV06_TCOV06_TCOV05 FOREIGN KEY(health_status_id)
	REFERENCES COV05_HEALTH_STATUS (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
	
ALTER TABLE COV07_HISTORY_LOCATION
	ADD CONSTRAINT FCOV07_TCOV07_TCOV03 FOREIGN KEY(location_id)
	REFERENCES COV03_LOCATION (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;
	
ALTER TABLE COV07_HISTORY_LOCATION
	ADD CONSTRAINT FCOV09_TCOV07_TCOV02 FOREIGN KEY(citizen_id)
	REFERENCES COV02_CITIZEN (id)
	ON DELETE RESTRICT ON UPDATE RESTRICT;