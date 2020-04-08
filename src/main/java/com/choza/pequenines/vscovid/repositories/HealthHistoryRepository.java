package com.choza.pequenines.vscovid.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HealthHistoryEntitie;

@Repository
public interface HealthHistoryRepository extends CrudRepository<HealthHistoryEntitie, Long>, HealthHistoryRepositoryComplemento {

	HealthHistoryEntitie findFirstByCitizenOrderByDateCreationDesc(CitizenEntitie citizen);
}
