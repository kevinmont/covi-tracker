package com.choza.pequenines.vscovid.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choza.pequenines.vscovid.repositories.entities.CitizenEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HistoryLocationEntitie;
import com.choza.pequenines.vscovid.repositories.entities.HistoryLocationEntitiePK;

@Repository
public interface HistoryLocationRepository extends CrudRepository<HistoryLocationEntitie, HistoryLocationEntitiePK> {

	Page<HistoryLocationEntitie> findAllByCitizen(CitizenEntitie citizen, Pageable pageable);

}
